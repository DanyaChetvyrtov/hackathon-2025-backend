package rnd.sueta.event_ms.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String ERROR_MESSAGES_SEPARATOR = "; ";
    public static final String VALIDATION_FAILED = "Validation failed";
    public static final String NOT_FOUND = "%s not found";
    public static final String POINT_OUT_OF_CITY = "Point is out of city area";
    public static final String ROUTE_POINTS_OUT_OF_CITY = "Can't generate route from points located out of city.";
    public static final String STRING_IS_TOO_LONG = "string is too long";
    public static final String NEGATIVE_PAGE_NUMBER = "Page number can't be negative value";
    public static final String INVALID_PAGE_SIZE = "Page size should be positive number";
}
