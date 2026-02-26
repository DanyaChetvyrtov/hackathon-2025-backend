package rnd.sueta.event_ms.model;

import lombok.Builder;
import rnd.sueta.event_ms.enums.PlaceType;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(toBuilder = true)
public record PlaceWithCoordinates(
        UUID id,

        String title,

        PlaceType type,

        UUID pointId,

        BigDecimal latitude,

        BigDecimal longitude
) {
}
