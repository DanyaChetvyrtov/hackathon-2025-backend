package rnd.sueta.event_ms.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import rnd.sueta.event_ms.controller.PlaceController;
import rnd.sueta.event_ms.dto.PlaceDto;
import rnd.sueta.event_ms.dto.request.CreatePlaceRq;
import rnd.sueta.event_ms.dto.request.UpdatePlaceRq;
import rnd.sueta.event_ms.dto.response.GetPlacesRs;
import rnd.sueta.event_ms.mapper.PlaceMapper;
import rnd.sueta.event_ms.model.PlaceWithCoordinates;
import rnd.sueta.event_ms.service.entity.PlaceService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PlaceControllerImpl implements PlaceController {
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;

    @Override
    public GetPlacesRs getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PlaceWithCoordinates> places = placeService.getAll(pageable);

        return GetPlacesRs.builder()
                .places(placeMapper.convert(places))
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
    public PlaceDto update(UUID id, UpdatePlaceRq updatePlaceRq) {
        PlaceWithCoordinates place = placeMapper.convert(updatePlaceRq);

        return placeMapper.convert(placeService.update(id, place));
    }

    @Override
    public void delete(UUID id) {
        placeService.delete(id);
    }
}
