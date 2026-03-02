package rnd.sueta.event_ms.service.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;

import java.util.UUID;

public interface PhotoMetaInfoService {
    Page<PhotoMetaInfo> getByOwnerId(UUID ownerId, Pageable pageable);

    PhotoMetaInfo getById(UUID id);

    PhotoMetaInfo create(UUID ownerId, PhotoMetaInfo photoMetaInfo);

    void delete(UUID id);
}
