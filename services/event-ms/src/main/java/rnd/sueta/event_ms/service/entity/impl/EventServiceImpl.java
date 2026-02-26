package rnd.sueta.event_ms.service.entity.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rnd.sueta.event_ms.enums.EventType;
import rnd.sueta.event_ms.model.DateTimeRange;
import rnd.sueta.event_ms.model.EventFilterParams;
import rnd.sueta.event_ms.model.EventWithPlace;
import rnd.sueta.event_ms.model.entity.Event;
import rnd.sueta.event_ms.model.entity.Point;
import rnd.sueta.event_ms.repository.EventRepository;
import rnd.sueta.event_ms.service.entity.EventService;
import rnd.sueta.event_ms.util.DateTimeRangeFactory;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public Page<EventWithPlace> getAll(EventFilterParams eventFilterParams) {
        DateTimeRange range = DateTimeRangeFactory.getRange(eventFilterParams.date(), 1);

        return eventFilterParams.categories() != null
                ? getAllByDateAndCategories(range, eventFilterParams)
                : getAllByDate(range, eventFilterParams);
    }

    @Override
    public List<EventWithPlace> getAllInRangeByCategories(Point startPoint, Point endPoint, List<EventType> categories) {
        return eventRepository.findAllInRangeByCategories(startPoint, endPoint, categories);
    }

    @Override
    public EventWithPlace getEventWithPlaceById(UUID id) {
        return eventRepository.findEventWithPlaceByEventId(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(
                event.toBuilder()
                        .id(UUID.randomUUID())
                        .build()
        );
    }

    @Transactional
    @Override
    public Event update(UUID id, Event event) {
        return eventRepository.save(
                event.toBuilder()
                        .id(id)
                        .build()
        );
    }

    @Override
    public void delete(UUID id) {
        eventRepository.deleteById(id);
    }

    private Page<EventWithPlace> getAllByDate(DateTimeRange range, EventFilterParams eventFilterParams) {
        return eventRepository.findEventsWithPlaceByDate(range, eventFilterParams.pageable());
    }

    private Page<EventWithPlace> getAllByDateAndCategories(DateTimeRange range, EventFilterParams eventFilterParams) {
        return eventRepository.findEventsWithPlaceByDateAndCategories(
                range,
                eventFilterParams.pageable(),
                eventFilterParams.categories());
    }
}
