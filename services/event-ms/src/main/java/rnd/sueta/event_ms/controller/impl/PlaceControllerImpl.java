package rnd.sueta.event_ms.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rnd.sueta.event_ms.controller.PlaceController;
import rnd.sueta.event_ms.dto.PhotoMetaInfoDto;
import rnd.sueta.event_ms.dto.PlaceDto;
import rnd.sueta.event_ms.dto.params.PaginationFilter;
import rnd.sueta.event_ms.dto.request.CreatePlaceRq;
import rnd.sueta.event_ms.dto.request.UpdatePlaceRq;
import rnd.sueta.event_ms.dto.response.GetPhotosMetaWithUrlRs;
import rnd.sueta.event_ms.dto.response.GetPlacesRs;
import rnd.sueta.event_ms.mapper.PhotoMapper;
import rnd.sueta.event_ms.mapper.PlaceMapper;
import rnd.sueta.event_ms.model.PhotoWithUrl;
import rnd.sueta.event_ms.model.PlaceWithCoordinates;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;
import rnd.sueta.event_ms.service.business.PlacePhotoManager;
import rnd.sueta.event_ms.service.entity.PlaceService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PlaceControllerImpl implements PlaceController {
    private final PlaceService placeService;
    private final PlacePhotoManager placePhotoManager;
    private final PlaceMapper placeMapper;
    private final PhotoMapper photoMapper;

    @Override
    public GetPlacesRs getAll(PaginationFilter paginationFilter) {
        Page<PlaceWithCoordinates> places = placeService.getAll(paginationFilter.page(), paginationFilter.size());

        return GetPlacesRs.builder()
                .places(placeMapper.convert(places))
                .build();
    }

    @Override
    public GetPhotosMetaWithUrlRs getAllByOwnerId(UUID id, PaginationFilter paginationFilter) {
        Page<PhotoWithUrl> photosMetaWithUrl = placePhotoManager.getAllByOwnerId(
                id, paginationFilter.page(), paginationFilter.size()
        );

        return GetPhotosMetaWithUrlRs.builder()
                .photosMetaWithUrl(photoMapper.convert(photosMetaWithUrl))
                .build();
    }

    @Override
    public PlaceDto getById(UUID id) {
        return placeMapper.convert(placeService.getById(id));
    }

    @Override
    public PlaceDto create(CreatePlaceRq createPlaceRq) {
        PlaceWithCoordinates place = placeMapper.convert(createPlaceRq);
        place = placeService.create(place);

        return placeMapper.convert(place);
    }

    @Override
    public PhotoMetaInfoDto uploadPhoto(UUID id, MultipartFile photo) {
        PhotoMetaInfo photoMetaInfo = placePhotoManager.createPhoto(id, photo);

        return photoMapper.convert(photoMetaInfo);
    }

    @Override
    public PlaceDto update(UUID id, UpdatePlaceRq updatePlaceRq) {
        PlaceWithCoordinates place = placeMapper.convert(updatePlaceRq);

        return placeMapper.convert(placeService.update(id, place));
    }

    @Override
    public void delete(UUID id) {
        placeService.delete(id);
    }

    @Override
    public void deletePhoto(UUID photoId) {
        placePhotoManager.deletePhoto(photoId);
    }
}
