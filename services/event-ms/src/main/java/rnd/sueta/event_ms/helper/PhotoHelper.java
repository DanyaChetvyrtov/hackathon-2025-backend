package rnd.sueta.event_ms.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rnd.sueta.event_ms.config.properties.AppMinioProperties;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public final class PhotoHelper {
    private static final String PATH_SEPARATOR = "%s/%s";
    private final AppMinioProperties appMinioProperties;

    public String getPhotoUrl(PhotoMetaInfo photoMetaInfo) {
        String hostWithBucket = PATH_SEPARATOR.formatted(appMinioProperties.getEndpoint(), appMinioProperties.getBucketName());
        String absolutePath = getAbsolutePhotoPath(photoMetaInfo);

        return PATH_SEPARATOR.formatted(hostWithBucket, absolutePath);
    }

    public String getAbsolutePhotoPath(PhotoMetaInfo photoMetaInfo) {
        String photoName = getNameWithExtension(photoMetaInfo.getId(), photoMetaInfo.getExtension());

        return PATH_SEPARATOR.formatted(
                photoMetaInfo.getOwnerType().toString().toLowerCase(),
                photoName
        );
    }

    private String getNameWithExtension(UUID id, String extension) {
        return "%s.%s".formatted(id, extension);
    }
}
