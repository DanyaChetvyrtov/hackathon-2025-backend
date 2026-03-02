package rnd.sueta.event_ms.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rnd.sueta.event_ms.controller.EventController;
import rnd.sueta.event_ms.dto.EventDto;
import rnd.sueta.event_ms.dto.EventWithPlaceDto;
import rnd.sueta.event_ms.dto.PhotoMetaInfoDto;
import rnd.sueta.event_ms.dto.params.EventsFilter;
import rnd.sueta.event_ms.dto.params.PaginationFilter;
import rnd.sueta.event_ms.dto.request.CreateEventRq;
import rnd.sueta.event_ms.dto.request.UpdateEventRq;
import rnd.sueta.event_ms.dto.response.GetEventsWithPlacesRs;
import rnd.sueta.event_ms.dto.response.GetPhotosMetaWithUrlRs;
import rnd.sueta.event_ms.mapper.EventMapper;
import rnd.sueta.event_ms.mapper.PhotoMapper;
import rnd.sueta.event_ms.mapper.RequestParamsMapper;
import rnd.sueta.event_ms.model.EventFilterParams;
import rnd.sueta.event_ms.model.EventWithPlace;
import rnd.sueta.event_ms.model.PhotoWithUrl;
import rnd.sueta.event_ms.model.entity.Event;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;
import rnd.sueta.event_ms.service.business.EventPhotoManager;
import rnd.sueta.event_ms.service.business.EventRegistrationService;
import rnd.sueta.event_ms.service.entity.EventService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {
    private final EventService eventService;
    private final EventPhotoManager eventPhotoManager;
    private final EventRegistrationService eventRegistrationService;
    private final EventMapper eventMapper;
    private final PhotoMapper photoMapper;
    private final RequestParamsMapper requestParamsMapper;

    @Override
    public GetEventsWithPlacesRs getAll(PaginationFilter paginationFilter, EventsFilter eventsFilter) {
        EventFilterParams eventFilterParams = requestParamsMapper.convert(
                eventsFilter, paginationFilter.page(), paginationFilter.size()
        );

        Page<EventWithPlace> events = eventService.getAllByFilter(eventFilterParams);

        return GetEventsWithPlacesRs.builder()
                .places(eventMapper.convert(events))
                .build();
    }

    @Override
    public GetPhotosMetaWithUrlRs getAllByOwnerId(UUID id, PaginationFilter paginationFilter) {
        Page<PhotoWithUrl> photosMetaWithUrl = eventPhotoManager.getAllByOwnerId(
                id, paginationFilter.page(), paginationFilter.size()
        );

        return GetPhotosMetaWithUrlRs.builder()
                .photosMetaWithUrl(photoMapper.convert(photosMetaWithUrl))
                .build();
    }

    @Override
    public EventWithPlaceDto getById(UUID id) {
        return eventMapper.convert(eventService.getEventWithPlaceById(id));
    }

    @Override
    public EventDto create(CreateEventRq createEventRq) {
        Event event = eventMapper.convert(createEventRq);

        return eventMapper.convert(eventRegistrationService.createEvent(event));
    }

    @Override
    public PhotoMetaInfoDto uploadPhoto(UUID id, MultipartFile photo) {
        PhotoMetaInfo photoMetaInfo = eventPhotoManager.createPhoto(id, photo);

        return photoMapper.convert(photoMetaInfo);
    }

    @Override
    public EventDto update(UUID id, UpdateEventRq updateEventRq) {
        Event event = eventMapper.convert(updateEventRq);

        return eventMapper.convert(eventRegistrationService.updateEvent(id, event));
    }

    @Override
    public void delete(UUID id) {
        eventService.delete(id);
    }

    @Override
    public void deletePhoto(UUID photoId) {
        eventPhotoManager.deletePhoto(photoId);
    }
}
