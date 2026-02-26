package rnd.sueta.event_ms.service.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rnd.sueta.event_ms.model.PlaceWithCoordinates;
import rnd.sueta.event_ms.model.entity.Point;

import java.util.List;
import java.util.UUID;

public interface PlaceService {
    Page<PlaceWithCoordinates> getAll(Pageable pageable);

    List<PlaceWithCoordinates> getByRouteId(UUID routeId);

    List<UUID> getAllIdsInRange(Point startPoint, Point endPoint);

    PlaceWithCoordinates getById(UUID id);

    PlaceWithCoordinates create(PlaceWithCoordinates place);

    PlaceWithCoordinates update(UUID id, PlaceWithCoordinates place);

    void delete(UUID id);

    boolean exists(UUID id);
}
