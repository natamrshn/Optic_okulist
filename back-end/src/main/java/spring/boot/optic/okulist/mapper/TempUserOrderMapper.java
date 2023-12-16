package spring.boot.optic.okulist.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.TempOrderRequestDto;
import spring.boot.optic.okulist.dto.TempOrderResponseDto;
import spring.boot.optic.okulist.model.TemporaryUser;

@Mapper(config = MapperConfig.class)
public interface TempUserOrderMapper {
    @Mapping(target = "products", source = "products")
    TemporaryUser toEntity(TempOrderRequestDto requestDto);

    TempOrderResponseDto toResponseDto(TemporaryUser entity);

    List<TemporaryUser> toEntities(List<TempOrderRequestDto> requestDtos);

    List<TempOrderResponseDto> toResponseDtos(List<TemporaryUser> entities);
}
