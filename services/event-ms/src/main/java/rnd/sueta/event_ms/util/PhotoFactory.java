package rnd.sueta.event_ms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import rnd.sueta.event_ms.model.PhotoWithUrl;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PhotoFactory {

    public static PhotoMetaInfo newDefaultPhotoMetaInfo(MultipartFile file) {
        String fileNameWithExtension = file.getOriginalFilename();

        String fileName = FilenameUtils.getBaseName(fileNameWithExtension);
        String extension = FilenameUtils.getExtension(fileNameWithExtension);

        return PhotoMetaInfo.builder()
                .originalFileName(fileName)
                .extension(extension)
                .fileSize((int) file.getSize())
                .contentType(file.getContentType())
                .build();
    }

    public static PhotoWithUrl newDefaultPhotoWithUrl(PhotoMetaInfo photoMetaInfo, String url) {
        return PhotoWithUrl.builder()
                .id(photoMetaInfo.getId())
                .originalFileName(photoMetaInfo.getOriginalFileName())
                .url(url)
                .build();
    }
}
