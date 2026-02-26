package rnd.sueta.event_ms.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rnd.sueta.event_ms.constants.ErrorMessages;
import rnd.sueta.event_ms.exception.custom.OutOfCityPointException;
import rnd.sueta.event_ms.model.entity.Point;
import rnd.sueta.event_ms.properties.BorderProperties;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public final class PointValidator {
    private final BorderProperties borders;

    public void checkPointWithinCityArea(Point point) {
        if (isPointOutOfCityArea(point)) {
            throw new OutOfCityPointException(ErrorMessages.POINT_OUT_OF_CITY);
        }
    }

    public void checkRoutePointsWithinCityArea(Point startRoutePoint, Point endRoutePoint) {
        if (isPointOutOfCityArea(startRoutePoint) || isPointOutOfCityArea(endRoutePoint)) {
            throw new OutOfCityPointException(ErrorMessages.ROUTE_POINTS_OUT_OF_CITY);
        }
    }

    private boolean isPointInCityArea(Point point) {
        BigDecimal minLon = borders.getBottomLeftCorner().getLongitude();
        BigDecimal maxLon = borders.getBottomRightCorner().getLongitude();
        BigDecimal minLat = borders.getBottomLeftCorner().getLatitude();
        BigDecimal maxLat = borders.getTopLeftCorner().getLatitude();

        return point.getLongitude().compareTo(minLon) >= 0 && point.getLongitude().compareTo(maxLon) <= 0
                && point.getLatitude().compareTo(minLat) >= 0 && point.getLatitude().compareTo(maxLat) <= 0;
    }

    private boolean isPointOutOfCityArea(Point point) {
        return !isPointInCityArea(point);
    }
}
