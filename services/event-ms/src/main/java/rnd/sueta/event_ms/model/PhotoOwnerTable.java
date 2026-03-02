package rnd.sueta.event_ms.model;

import lombok.Builder;
import org.jooq.Field;
import org.jooq.Table;

import java.util.UUID;

@Builder(toBuilder = true)
public record PhotoOwnerTable(Table<?> table, Field<UUID> id) {
}
