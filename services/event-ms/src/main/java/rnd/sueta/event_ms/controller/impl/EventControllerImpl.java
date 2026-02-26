package rnd.sueta.event_ms.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import rnd.sueta.event_ms.controller.EventController;
import rnd.sueta.event_ms.dto.EventDto;
import rnd.sueta.event_ms.dto.EventWithPlaceDto;
import rnd.sueta.event_ms.dto.request.CreateEventRq;
import rnd.sueta.event_ms.dto.request.UpdateEventRq;
import rnd.sueta.event_ms.enums.EventType;
import rnd.sueta.event_ms.mapper.EventMapper;
import rnd.sueta.event_ms.model.EventFilterParams;
import rnd.sueta.event_ms.model.EventWithPlace;
import rnd.sueta.event_ms.model.entity.Event;
import rnd.sueta.event_ms.service.business.EventRegistrationService;
import rnd.sueta.event_ms.service.entity.EventService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {
    private final EventService eventService;
    private final EventRegistrationService eventRegistrationService;
    private final EventMapper eventMapper;

    @Override
    public Page<EventWithPlaceDto> getAll(int page, int size, OffsetDateTime date, List<EventType> categories) {
        Pageable pageable = PageRequest.of(page, size);

        EventFilterParams eventFilterParams = EventFilterParams.builder()
                .pageable(pageable)
                .date(Objects.requireNonNullElseGet(date, OffsetDateTime::now))
                .categories(categories)
                .build();

        Page<EventWithPlace> events = eventService.getAll(eventFilterParams);

        return eventMapper.convert(events);
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
    public EventDto update(UUID id, UpdateEventRq updateEventRq) {
        Event event = eventMapper.convert(updateEventRq);

        return eventMapper.convert(eventRegistrationService.updateEvent(id, event));
    }

    @Override
    public void delete(UUID id) {
        eventService.delete(id);
    }
}
