package rnd.sueta.event_ms.validator;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import rnd.sueta.event_ms.constants.ErrorMessages;

@Component
public final class PlaceValidator {
    public void checkPlaceExistence(boolean exists) {
        if (!exists) {
            throw new EntityNotFoundException(
                    String.format(ErrorMessages.NOT_FOUND, "Place")
            );
        }
    }
}
