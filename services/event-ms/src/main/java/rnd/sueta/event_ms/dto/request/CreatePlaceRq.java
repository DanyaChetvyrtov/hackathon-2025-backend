package rnd.sueta.event_ms.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import rnd.sueta.event_ms.constants.ErrorMessages;
import rnd.sueta.event_ms.constants.PlaceConstants;
import rnd.sueta.event_ms.constants.ValidationMessages;
import rnd.sueta.event_ms.enums.PlaceType;

import java.math.BigDecimal;

public record CreatePlaceRq(
        @NotBlank(message = "title " + ValidationMessages.IS_REQUIRED)
        @Size(max = PlaceConstants.MAX_TITLE_LENGTH, message = "title " + ErrorMessages.STRING_IS_TOO_LONG)
        String title,

        @NotNull(message = "eventType " + ValidationMessages.IS_REQUIRED)
        PlaceType type,

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
