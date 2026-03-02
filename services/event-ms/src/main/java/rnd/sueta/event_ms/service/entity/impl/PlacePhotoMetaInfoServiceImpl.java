package rnd.sueta.event_ms.service.entity.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;
import rnd.sueta.event_ms.repository.PlacePhotoMetaInfoRepository;
import rnd.sueta.event_ms.service.entity.PlacePhotoMetaInfoService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlacePhotoMetaInfoServiceImpl implements PlacePhotoMetaInfoService {
    private final PlacePhotoMetaInfoRepository placePhotoMetaInfoRepository;

    @Override
    public Page<PhotoMetaInfo> getByOwnerId(UUID ownerId, Pageable pageable) {
        return placePhotoMetaInfoRepository.findAllByPlaceId(ownerId, pageable);
    }

    @Override
    public PhotoMetaInfo getById(UUID id) {
        return placePhotoMetaInfoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public PhotoMetaInfo create(UUID ownerId, PhotoMetaInfo photoMetaInfo) {
        return placePhotoMetaInfoRepository.save(
                ownerId,
                photoMetaInfo.toBuilder()
                        .id(UUID.randomUUID())
                        .build()
        );

    }

    @Override
    public void delete(UUID id) {
        placePhotoMetaInfoRepository.deleteById(id);
    }
}
