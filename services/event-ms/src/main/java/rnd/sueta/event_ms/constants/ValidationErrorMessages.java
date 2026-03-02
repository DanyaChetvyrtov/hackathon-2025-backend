package rnd.sueta.event_ms.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationErrorMessages {
    public static final String IS_REQUIRED = "is required";
    public static final String STRING_IS_TOO_LONG = "value can't be longer than {max}";
    public static final String BIG_DECIMAL_VALID_RANGE =
            "must have up to {integer} integer digits and {fraction} decimal";
    public static final String POSITIVE_VALUE = "must be greater than 0";

    public static final String NEGATIVE_PAGE_NUMBER = "Page number can't be negative value";
    public static final String INVALID_PAGE_SIZE = "Page size should be positive number";
    public static final String OWNER_TYPE_SIZE = "ownerType should be between {min} and {max}";

    public static final String MAX_LATITUDE_VALUE =
            "latitude cannot be further north than the North Pole (maximum {value}°)";
    public static final String MIN_LATITUDE_VALUE =
            "latitude cannot be further south than the South Pole (minimum {value}°)";
    public static final String MAX_LONGITUDE_VALUE = "longitude cannot be east of the antimeridian (maximum {value}°)";
    public static final String MIN_LONGITUDE_VALUE = "longitude cannot be west of the antimeridian (minimum {value}°)";
}
