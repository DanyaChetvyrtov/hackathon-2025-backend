package rnd.sueta.event_ms.mapper.jooq;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;
import rnd.sueta.event_ms.enums.PlaceType;
import rnd.sueta.event_ms.model.PlaceWithCoordinates;

@Component
public class PlaceWithCoordinatesMapper implements RecordMapper<Record, PlaceWithCoordinates> {
    @Override
    public PlaceWithCoordinates map(Record record) {
        return PlaceWithCoordinates.builder()
                .id(record.get(Tables.PLACES.ID))
                .title(record.get(Tables.PLACES.TITLE))
                .type(PlaceType.valueOf(record.get(Tables.PLACES.TYPE)))
                .pointId(record.get(Tables.POINTS.ID))
                .latitude(record.get(Tables.POINTS.LATITUDE))
                .longitude(record.get(Tables.POINTS.LONGITUDE))
                .build();
    }
}
