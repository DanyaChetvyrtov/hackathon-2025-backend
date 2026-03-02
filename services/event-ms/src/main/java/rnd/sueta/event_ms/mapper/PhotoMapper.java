package rnd.sueta.event_ms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import rnd.sueta.event_ms.config.BaseMapperConfig;
import rnd.sueta.event_ms.dto.PhotoMetaInfoDto;
import rnd.sueta.event_ms.model.PhotoWithUrl;
import rnd.sueta.event_ms.model.entity.PhotoMetaInfo;

import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public interface PhotoMapper {
    @Mapping(target = "url", ignore = true)
    PhotoMetaInfoDto convert(PhotoMetaInfo source);

    @Mapping(target = "ownerType", ignore = true)
    @Mapping(target = "extension", ignore = true)
    @Mapping(target = "fileSize", ignore = true)
    @Mapping(target = "contentType", ignore = true)
    PhotoMetaInfoDto convert(PhotoWithUrl source);

    List<PhotoMetaInfoDto> convert(List<PhotoWithUrl> source);

    default Page<PhotoMetaInfoDto> convert(Page<PhotoWithUrl> source) {
        return source.map(this::convert);
    }
}
