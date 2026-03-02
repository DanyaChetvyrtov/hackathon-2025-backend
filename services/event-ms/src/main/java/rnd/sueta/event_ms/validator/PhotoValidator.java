package rnd.sueta.event_ms.validator;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import rnd.sueta.event_ms.constants.ErrorMessages;
import rnd.sueta.event_ms.constants.PhotoErrorMessages;
import rnd.sueta.event_ms.enums.PhotoExtension;
import rnd.sueta.event_ms.enums.PhotoOwnerType;
import rnd.sueta.event_ms.exception.custom.InvalidPhotoExtension;
import rnd.sueta.event_ms.exception.custom.InvalidPhotoOwnerType;

@Component
public final class PhotoValidator {
    public void checkPhotoExtension(MultipartFile photo) {
        String extension = FilenameUtils.getExtension(photo.getOriginalFilename());

        if (extension == null) {
            throw new InvalidPhotoExtension(PhotoErrorMessages.INVALID_PHOTO_EXTENSION);
        }

        try {
            PhotoExtension.valueOf(extension.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidPhotoExtension(PhotoErrorMessages.INVALID_PHOTO_EXTENSION);
        }
    }

    public void checkOwnerType(String ownerType) {
        if (ownerType == null) {
            throw new InvalidPhotoOwnerType(PhotoErrorMessages.INVALID_OWNER_TYPE);
        }

        try {
            PhotoOwnerType.valueOf(ownerType.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new InvalidPhotoOwnerType(PhotoErrorMessages.INVALID_OWNER_TYPE);
        }
    }

    public void checkOwnerExistence(boolean exists) {
        if (!exists) {
            throw new EntityNotFoundException(ErrorMessages.NOT_FOUND.formatted("Owner"));
        }
    }
}
