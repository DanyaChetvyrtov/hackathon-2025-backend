package rnd.sueta.event_ms.service.entity.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;
import rnd.sueta.event_ms.repository.EventPhotoMetaInfoRepository;
import rnd.sueta.event_ms.service.entity.EventPhotoMetaInfoService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventPhotoMetaInfoServiceImpl implements EventPhotoMetaInfoService {
    private final EventPhotoMetaInfoRepository eventPhotoMetaInfoRepository;

    @Override
    public Page<PhotoMetaInfo> getByOwnerId(UUID ownerId, Pageable pageable) {
        return eventPhotoMetaInfoRepository.findAllByPlaceId(ownerId, pageable);
    }

    @Override
    public PhotoMetaInfo getById(UUID id) {
        return eventPhotoMetaInfoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public PhotoMetaInfo create(UUID ownerId, PhotoMetaInfo photoMetaInfo) {
        return eventPhotoMetaInfoRepository.save(
                ownerId,
                photoMetaInfo.toBuilder()
                        .id(UUID.randomUUID())
                        .build()
        );

    }

    @Override
    public void delete(UUID id) {
        eventPhotoMetaInfoRepository.deleteById(id);
    }
}
