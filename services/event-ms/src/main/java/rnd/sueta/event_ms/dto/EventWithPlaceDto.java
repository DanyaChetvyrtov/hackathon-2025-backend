package rnd.sueta.event_ms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import rnd.sueta.event_ms.enums.EventType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EventWithPlaceDto(
        UUID eventId,

        UUID placeId,

        BigDecimal latitude,

        BigDecimal longitude,

        String title,

        EventType eventType,

        OffsetDateTime eventStart,

        OffsetDateTime eventEnd,

        BigDecimal price,

        Integer ageRestriction
) {
}
