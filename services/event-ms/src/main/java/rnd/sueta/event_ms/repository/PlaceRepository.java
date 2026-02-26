package rnd.sueta.event_ms.repository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.generated.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import rnd.sueta.event_ms.mapper.jooq.PlaceWithCoordinatesMapper;
import rnd.sueta.event_ms.model.PlaceWithCoordinates;
import rnd.sueta.event_ms.model.entity.Point;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlaceRepository {
    private final DSLContext dsl;
    private final PlaceWithCoordinatesMapper placeWithCoordinatesMapper;

    public Page<PlaceWithCoordinates> findAll(Pageable pageable) {
        long total = dsl.fetchCount(Tables.PLACES);

        List<PlaceWithCoordinates> places = dsl.select()
                .from(Tables.PLACES)
                .join(Tables.POINTS)
                .on(Tables.POINTS.ID.eq(Tables.PLACES.POINT_ID))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .map(placeWithCoordinatesMapper);

        return new PageImpl<>(places, pageable, total);
    }

    public List<PlaceWithCoordinates> findAllByRouteId(UUID routeId) {
        return dsl.select()
                .from(Tables.PLACES)
                .join(Tables.ROUTE_PLACES)
                .on(Tables.PLACES.ID.eq(Tables.ROUTE_PLACES.PLACE_ID))
                .join(Tables.POINTS)
                .on(Tables.POINTS.ID.eq(Tables.PLACES.POINT_ID))
                .where(Tables.ROUTE_PLACES.ROUTE_ID.eq(routeId))
                .fetch()
                .map(placeWithCoordinatesMapper);
    }

    public List<UUID> findAllIdsInRange(Point startPoint, Point endPoint) {
        return dsl.select()
                .from(Tables.PLACES)
                .join(Tables.POINTS)
                .on(Tables.POINTS.ID.eq(Tables.PLACES.POINT_ID))
                .where(Tables.POINTS.LATITUDE.between(startPoint.getLatitude(), endPoint.getLatitude()))
                .and(Tables.POINTS.LONGITUDE.between(startPoint.getLongitude(), endPoint.getLongitude()))
                .fetch(Tables.PLACES.ID, UUID.class);
    }

    public Optional<PlaceWithCoordinates> findById(UUID id) {
        return dsl.select()
                .from(Tables.PLACES)
                .join(Tables.POINTS)
                .on(Tables.POINTS.ID.eq(Tables.PLACES.POINT_ID))
                .where(Tables.PLACES.ID.eq(id))
                .fetchOptional()
                .map(placeWithCoordinatesMapper);
    }

    public PlaceWithCoordinates save(PlaceWithCoordinates place) {
        Point savedPoint = insertPoint(place);

        if (savedPoint == null) {
            savedPoint = getPoint(place.latitude(), place.longitude());
        }

        PlaceWithCoordinates placeWithSavedPoint = place.toBuilder()
                .pointId(savedPoint.getId())
                .build();

        dsl.insertInto(Tables.PLACES)
                .set(dsl.newRecord(Tables.PLACES, placeWithSavedPoint))
                .onConflict(Tables.PLACES.ID)
                .doUpdate()
                .set(dsl.newRecord(Tables.PLACES, placeWithSavedPoint))
                .execute();

        return place;
    }

    public void deleteById(UUID id) {
        dsl.deleteFrom(Tables.PLACES)
                .where(Tables.PLACES.ID.eq(id))
                .execute();
    }

    public boolean existsById(UUID id) {
        return dsl.fetchExists(dsl.selectOne()
                .from(Tables.PLACES)
                .where(Tables.PLACES.ID.eq(id)));
    }

    private Point insertPoint(PlaceWithCoordinates place) {
        Point point = Point.builder()
                .id(UUID.randomUUID())
                .latitude(place.latitude())
                .longitude(place.longitude())
                .build();

        return dsl.insertInto(Tables.POINTS)
                .set(dsl.newRecord(Tables.POINTS, point))
                .onConflict()
                .doNothing()
                .returning()
                .fetchOne(jooqRecord -> jooqRecord.into(Point.class));
    }

    private Point getPoint(BigDecimal latitude, BigDecimal longitude) {
        return dsl.select()
                .from(Tables.POINTS)
                .where(Tables.POINTS.LATITUDE.eq(latitude))
                .and(Tables.POINTS.LONGITUDE.eq(longitude))
                .fetchOneInto(Point.class);
    }
}
