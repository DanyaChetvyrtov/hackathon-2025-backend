package rnd.sueta.event_ms.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import rnd.sueta.event_ms.constants.ErrorMessages;
import rnd.sueta.event_ms.constants.EventConstants;
import rnd.sueta.event_ms.constants.ValidationMessages;
import rnd.sueta.event_ms.enums.EventType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record UpdateEventRq(
        @NotBlank(message = "title " + ValidationMessages.IS_REQUIRED)
        @Size(max = EventConstants.MAX_TITLE_LENGTH, message = "title " + ErrorMessages.STRING_IS_TOO_LONG)
        String title,

        @NotNull(message = "eventType " + ValidationMessages.IS_REQUIRED)
        EventType type,

        @NotNull(message = "eventStart " + ValidationMessages.IS_REQUIRED)
        @FutureOrPresent
        OffsetDateTime eventStart,

        @NotNull(message = "eventEnd " + ValidationMessages.IS_REQUIRED)
        @FutureOrPresent
        OffsetDateTime eventEnd,

        @NotNull(message = "price " + ValidationMessages.IS_REQUIRED)
        @DecimalMin(
                value = EventConstants.MIN_PRICE_VALUE,
                message = "price" + ValidationMessages.POSITIVE_VALUE)
        @Digits(
                integer = EventConstants.PRICE_PRECISION,
                fraction = EventConstants.PRICE_SCALE,
                message = ValidationMessages.BIG_DECIMAL_VALID_RANGE)
        BigDecimal price,

        @Min(
                value = EventConstants.MIN_AGE_VALUE,
                message = "ageRestriction " + ValidationMessages.POSITIVE_VALUE)
        Integer ageRestriction,

        @NotNull(message = "placeId " + ValidationMessages.IS_REQUIRED)
        UUID placeId
) {
}
