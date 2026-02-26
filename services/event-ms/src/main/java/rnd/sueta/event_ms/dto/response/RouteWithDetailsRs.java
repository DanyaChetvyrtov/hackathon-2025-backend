package rnd.sueta.event_ms.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import rnd.sueta.event_ms.dto.EventWithPlaceDto;

import java.util.List;
import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RouteWithDetailsRs(
        UUID id,

        UUID profileId,

        List<EventWithPlaceDto> events
) {
}
