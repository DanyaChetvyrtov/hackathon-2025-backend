package rnd.sueta.event_ms.model;

import lombok.Builder;
import org.springframework.data.domain.Pageable;
import rnd.sueta.event_ms.enums.EventType;

import java.time.OffsetDateTime;
import java.util.List;

@Builder(toBuilder = true)
public record EventFilterParams(
        Pageable pageable,

        OffsetDateTime date,

        List<EventType> categories
) {
}
