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
import rnd.sueta.event_ms.dto.EventDto;
import rnd.sueta.event_ms.dto.EventWithPlaceDto;
import rnd.sueta.event_ms.dto.PhotoMetaInfoDto;
import rnd.sueta.event_ms.dto.params.EventsFilter;
import rnd.sueta.event_ms.dto.params.PaginationFilter;
import rnd.sueta.event_ms.dto.request.CreateEventRq;
import rnd.sueta.event_ms.dto.request.UpdateEventRq;
import rnd.sueta.event_ms.dto.response.GetEventsWithPlacesRs;
import rnd.sueta.event_ms.dto.response.GetPhotosMetaWithUrlRs;

import java.util.UUID;

@RequestMapping("/api/v1/events")
public interface EventController {
    @GetMapping
    GetEventsWithPlacesRs getAll(@Valid PaginationFilter paginationFilter, @Valid EventsFilter eventsFilter);

    @GetMapping("/{id}/photos")
    GetPhotosMetaWithUrlRs getAllByOwnerId(@PathVariable UUID id, @Valid PaginationFilter paginationFilter);

    @GetMapping("/{id}")
    EventWithPlaceDto getById(@PathVariable UUID id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EventDto create(@RequestBody @Valid CreateEventRq createEventRq);

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    PhotoMetaInfoDto uploadPhoto(@PathVariable UUID id, MultipartFile photo);

    @PutMapping("/{id}")
    EventDto update(@PathVariable UUID id, @RequestBody @Valid UpdateEventRq updateEventRq);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id);

    @DeleteMapping("/{id}/photos/{photoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePhoto(@PathVariable UUID photoId);
}
