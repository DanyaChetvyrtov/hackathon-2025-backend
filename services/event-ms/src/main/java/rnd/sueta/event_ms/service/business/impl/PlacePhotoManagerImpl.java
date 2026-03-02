package rnd.sueta.event_ms.service.business.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rnd.sueta.event_ms.enums.PhotoOwnerType;
import rnd.sueta.event_ms.helper.PhotoHelper;
import rnd.sueta.event_ms.model.PhotoWithUrl;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;
import rnd.sueta.event_ms.service.business.PlacePhotoManager;
import rnd.sueta.event_ms.service.entity.PhotoService;
import rnd.sueta.event_ms.service.entity.PlacePhotoMetaInfoService;
import rnd.sueta.event_ms.service.entity.PlaceService;
import rnd.sueta.event_ms.util.PhotoFactory;
import rnd.sueta.event_ms.validator.PhotoValidator;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlacePhotoManagerImpl implements PlacePhotoManager {

    private final PhotoService photoService;
    private final PlaceService placeService;
    private final PlacePhotoMetaInfoService placePhotoMetaInfoService;
    private final PhotoValidator photoValidator;
    private final PhotoHelper photoHelper;

    @Override
    public Page<PhotoWithUrl> getAllByOwnerId(UUID ownerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<PhotoMetaInfo> photosPage = placePhotoMetaInfoService.getByOwnerId(ownerId, pageable);
        return photosPage.map(photoMetaInfo -> {
            String url = photoHelper.getPhotoUrl(photoMetaInfo);

            return PhotoFactory.newDefaultPhotoWithUrl(photoMetaInfo, url);
        });
    }

    @Override
    public PhotoMetaInfo createPhoto(UUID ownerId, MultipartFile photo) {
        photoValidator.checkPhotoExtension(photo);

        boolean ownerExists = placeService.exists(ownerId);
        photoValidator.checkOwnerExistence(ownerExists);

        PhotoMetaInfo photoMetaInfo = PhotoFactory.newDefaultPhotoMetaInfo(photo).toBuilder()
                .ownerType(PhotoOwnerType.PLACES)
                .build();

        PhotoMetaInfo savedPhotoMeta = placePhotoMetaInfoService.create(ownerId, photoMetaInfo);

        String absolutePhotoPath = photoHelper.getAbsolutePhotoPath(
                photoMetaInfo.toBuilder()
                        .id(savedPhotoMeta.getId())
                        .build()
        );
        photoService.save(absolutePhotoPath, photo);

        return savedPhotoMeta;
    }

    @Override
    public void deletePhoto(UUID id) {
        try {
            PhotoMetaInfo photoMetaInfo = placePhotoMetaInfoService.getById(id);
            String absolutePhotoPath = photoHelper.getAbsolutePhotoPath(photoMetaInfo);

            placePhotoMetaInfoService.delete(id);
            photoService.delete(absolutePhotoPath);
        } catch (EntityNotFoundException exception) {
            log.debug("Photo with id {} not found, deletion skipped", id);
        }
    }
}
