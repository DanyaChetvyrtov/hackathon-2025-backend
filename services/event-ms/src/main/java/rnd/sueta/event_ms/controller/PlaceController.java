package rnd.sueta.event_ms.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import rnd.sueta.event_ms.dto.PhotoMetaInfoDto;
import rnd.sueta.event_ms.dto.PlaceDto;
import rnd.sueta.event_ms.dto.params.PaginationFilter;
import rnd.sueta.event_ms.dto.request.CreatePlaceRq;
import rnd.sueta.event_ms.dto.request.UpdatePlaceRq;
import rnd.sueta.event_ms.dto.response.GetPhotosMetaWithUrlRs;
import rnd.sueta.event_ms.dto.response.GetPlacesRs;

import java.util.UUID;

@RequestMapping("/api/v1/places")
public interface PlaceController {
    @GetMapping
    GetPlacesRs getAll(@Valid PaginationFilter paginationFilter);

    @GetMapping("/{id}/photos")
    GetPhotosMetaWithUrlRs getAllByOwnerId(@PathVariable UUID id, @Valid PaginationFilter paginationFilter);

    @GetMapping("/{id}")
    PlaceDto getById(@PathVariable UUID id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PlaceDto create(@RequestBody @Valid CreatePlaceRq createPlaceRq);

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PhotoMetaInfoDto uploadPhoto(@PathVariable UUID id, MultipartFile photo);

    @PutMapping("/{id}")
    PlaceDto update(@PathVariable UUID id, @RequestBody @Valid UpdatePlaceRq updatePlaceRq);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id);

    @DeleteMapping("/{id}/photos/{photoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePhoto(@PathVariable UUID photoId);
}
