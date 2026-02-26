package rnd.sueta.event_ms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import rnd.sueta.event_ms.enums.PlaceType;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlaceDto(
        UUID id,

        String title,

        PlaceType type,

        BigDecimal latitude,

        BigDecimal longitude
) {
}
