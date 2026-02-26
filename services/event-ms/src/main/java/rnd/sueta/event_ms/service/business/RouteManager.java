package rnd.sueta.event_ms.service.business;

import rnd.sueta.event_ms.model.RouteGenerationParams;
import rnd.sueta.event_ms.model.RouteWithEvents;
import rnd.sueta.event_ms.model.RouteWithPlaces;

import java.util.UUID;

public interface RouteManager {
    RouteWithPlaces getRoute(UUID routeId);

    RouteWithEvents generateRoute(RouteGenerationParams routeGenerationParams);
}
