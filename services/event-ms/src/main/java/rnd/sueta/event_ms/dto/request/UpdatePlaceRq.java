package rnd.sueta.event_ms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import rnd.sueta.event_ms.constants.ErrorMessages;
import rnd.sueta.event_ms.constants.PlaceConstants;
import rnd.sueta.event_ms.constants.ValidationMessages;
import rnd.sueta.event_ms.enums.PlaceType;

public record UpdatePlaceRq(
        @NotBlank(message = "title " + ValidationMessages.IS_REQUIRED)
        @Size(max = PlaceConstants.MAX_TITLE_LENGTH, message = "title " + ErrorMessages.STRING_IS_TOO_LONG)
        String title,

        @NotNull(message = "eventType " + ValidationMessages.IS_REQUIRED)
        PlaceType type
) {
}
