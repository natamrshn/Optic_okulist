package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;

@Mapper(config = MapperConfig.class)
public interface TempUserOrderMapper {
    /*
    @Mapping(target = "products", source = "products")
    TemporaryUser toEntity(TempOrderRequestDto requestDto);

    TempOrderResponseDto toResponseDto(TemporaryUser entity);

    List<TemporaryUser> toEntities(List<TempOrderRequestDto> requestDtos);

    List<TempOrderResponseDto> toResponseDtos(List<TemporaryUser> entities);*/
}
