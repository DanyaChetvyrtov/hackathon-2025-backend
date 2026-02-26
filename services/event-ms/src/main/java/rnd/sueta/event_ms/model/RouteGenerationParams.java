package rnd.sueta.event_ms.model;

import lombok.Builder;
import rnd.sueta.event_ms.enums.EventType;
import rnd.sueta.event_ms.model.entity.Point;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
public record RouteGenerationParams(
        UUID profileId,

        Point startPoint,

        Point endPoint,

        BigDecimal budget,

        List<EventType> categories
) {
}
