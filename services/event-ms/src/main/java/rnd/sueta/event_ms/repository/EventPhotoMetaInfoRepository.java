package rnd.sueta.event_ms.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.generated.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EventPhotoMetaInfoRepository implements PhotoMetaInfoRepository {
    private final DSLContext dsl;

    @Override
    public Page<PhotoMetaInfo> findAllByPlaceId(UUID ownerId, Pageable pageable) {
        List<PhotoMetaInfo> photos = dsl.select(Tables.PHOTOS)
                .from(Tables.PHOTOS)
                .join(Tables.EVENT_PHOTOS)
                .on(Tables.EVENT_PHOTOS.PHOTO_ID.eq(Tables.PHOTOS.ID))
                .where(Tables.EVENT_PHOTOS.OWNER_ID.eq(ownerId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchInto(PhotoMetaInfo.class);

        long total = countOwnerPhotos(ownerId);

        return new PageImpl<>(photos, pageable, total);
    }

    @Override
    public Optional<PhotoMetaInfo> findById(UUID id) {
        return dsl.select()
                .from(Tables.PHOTOS)
                .where(Tables.PHOTOS.ID.eq(id))
                .fetchOptionalInto(PhotoMetaInfo.class);
    }

    @Override
    public PhotoMetaInfo save(UUID ownerId, PhotoMetaInfo photoMetaInfo) {
        PhotoMetaInfo savedPhotoMeta = dsl.insertInto(Tables.PHOTOS)
                .set(dsl.newRecord(Tables.PHOTOS, photoMetaInfo))
                .onConflict(Tables.PHOTOS.ID)
                .doUpdate()
                .set(dsl.newRecord(Tables.PHOTOS, photoMetaInfo))
                .returning()
                .fetchOne(jooqRecord -> jooqRecord.into(PhotoMetaInfo.class));

        dsl.insertInto(Tables.EVENT_PHOTOS)
                .set(Tables.EVENT_PHOTOS.PHOTO_ID, photoMetaInfo.getId())
                .set(Tables.EVENT_PHOTOS.OWNER_ID, ownerId)
                .execute();

        return savedPhotoMeta;
    }

    @Override
    public void deleteById(UUID id) {
        dsl.deleteFrom(Tables.PHOTOS)
                .where(Tables.PHOTOS.ID.eq(id))
                .execute();
    }

    private Long countOwnerPhotos(UUID ownerId) {
        Long total = dsl.selectCount()
                .from(Tables.PHOTOS)
                .join(Tables.EVENT_PHOTOS)
                .on(Tables.EVENT_PHOTOS.PHOTO_ID.eq(Tables.PHOTOS.ID))
                .where(Tables.EVENT_PHOTOS.OWNER_ID.eq(ownerId))
                .fetchOneInto(Long.class);

        return Objects.requireNonNullElse(total, 0L);
    }
}
