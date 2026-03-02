package rnd.sueta.event_ms.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import rnd.sueta.event_ms.constants.PlaceConstants;
import rnd.sueta.event_ms.constants.ValidationErrorMessages;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record PointDto(
        @NotNull(message = "latitude " + ValidationErrorMessages.IS_REQUIRED)
        @Digits(
                integer = PlaceConstants.COORDINATE_PRECISION,
                fraction = PlaceConstants.COORDINATE_SCALE,
                message = "latitude " + ValidationErrorMessages.BIG_DECIMAL_VALID_RANGE)
        @DecimalMin(
                value = PlaceConstants.MIN_LATITUDE_VALUE,
                message = ValidationErrorMessages.MIN_LATITUDE_VALUE)
        @DecimalMax(
                value = PlaceConstants.MAX_LATITUDE_VALUE,
                message = ValidationErrorMessages.MAX_LATITUDE_VALUE
        )
        BigDecimal latitude,

        @NotNull(message = "longitude " + ValidationErrorMessages.IS_REQUIRED)
        @Digits(
                integer = PlaceConstants.COORDINATE_PRECISION,
                fraction = PlaceConstants.COORDINATE_SCALE,
                message = "longitude " + ValidationErrorMessages.BIG_DECIMAL_VALID_RANGE)
        @DecimalMin(
                value = PlaceConstants.MIN_LONGITUDE_VALUE,
                message = ValidationErrorMessages.MIN_LONGITUDE_VALUE)
        @DecimalMax(
                value = PlaceConstants.MAX_LONGITUDE_VALUE,
                message = ValidationErrorMessages.MAX_LONGITUDE_VALUE
        )
        BigDecimal longitude
) {
}
