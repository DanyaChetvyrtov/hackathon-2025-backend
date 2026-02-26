package rnd.sueta.event_ms.service.entity;

import rnd.sueta.event_ms.model.EventWithPlace;
import rnd.sueta.event_ms.model.entity.Route;

import java.util.List;
import java.util.UUID;

public interface RouteService {
    Route getById(UUID routeId);

    Route saveRouteWithPlaces(List<EventWithPlace> places, UUID profileId);
}
