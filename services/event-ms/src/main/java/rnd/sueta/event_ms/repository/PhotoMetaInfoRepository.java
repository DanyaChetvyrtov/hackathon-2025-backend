package rnd.sueta.event_ms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;

import java.util.Optional;
import java.util.UUID;

public interface PhotoMetaInfoRepository {
    Page<PhotoMetaInfo> findAllByPlaceId(UUID ownerId, Pageable pageable);

    Optional<PhotoMetaInfo> findById(UUID id);

    PhotoMetaInfo save(UUID ownerId, PhotoMetaInfo photoMetaInfo);

    void deleteById(UUID id);
}
