package rnd.sueta.event_ms.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import rnd.sueta.event_ms.constants.PlaceConstants;
import rnd.sueta.event_ms.constants.ValidationMessages;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record PointDto(
        @NotNull(message = "latitude " + ValidationMessages.IS_REQUIRED)
        @Digits(
                integer = PlaceConstants.COORDINATE_PRECISION,
                fraction = PlaceConstants.COORDINATE_SCALE,
                message = "latitude " + ValidationMessages.BIG_DECIMAL_VALID_RANGE)
        @DecimalMin(
                value = PlaceConstants.MIN_LATITUDE_VALUE,
                message = ValidationMessages.MIN_LATITUDE_VALUE)
        @DecimalMax(
                value = PlaceConstants.MAX_LATITUDE_VALUE,
                message = ValidationMessages.MAX_LATITUDE_VALUE
        )
        BigDecimal latitude,

        @NotNull(message = "longitude " + ValidationMessages.IS_REQUIRED)
        @Digits(
                integer = PlaceConstants.COORDINATE_PRECISION,
                fraction = PlaceConstants.COORDINATE_SCALE,
                message = "longitude " + ValidationMessages.BIG_DECIMAL_VALID_RANGE)
        @DecimalMin(
                value = PlaceConstants.MIN_LONGITUDE_VALUE,
                message = ValidationMessages.MIN_LONGITUDE_VALUE)
        @DecimalMax(
                value = PlaceConstants.MAX_LONGITUDE_VALUE,
                message = ValidationMessages.MAX_LONGITUDE_VALUE
        )
        BigDecimal longitude
) {
}
