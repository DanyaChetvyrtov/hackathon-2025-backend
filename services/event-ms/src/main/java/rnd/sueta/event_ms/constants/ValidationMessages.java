package rnd.sueta.event_ms.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationMessages {
    public static final String IS_REQUIRED = "is required";
    public static final String REQUIRED_SIZE = "must be between {min} and {max} characters";
    public static final String BIG_DECIMAL_VALID_RANGE =
            "must have up to {integer} integer digits and {fraction} decimal events";
    public static final String POSITIVE_VALUE = "must be greater than 0";

    public static final String MAX_LATITUDE_VALUE =
            "latitude cannot be further north than the North Pole (maximum {value}°)";
    public static final String MIN_LATITUDE_VALUE =
            "latitude cannot be further south than the South Pole (minimum {value}°)";
    public static final String MAX_LONGITUDE_VALUE = "longitude cannot be east of the antimeridian (maximum {value}°)";
    public static final String MIN_LONGITUDE_VALUE = "longitude cannot be west of the antimeridian (minimum {value}°)";
}
