package spring.boot.optic.okulist.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.model.Glasses;

@Mapper(config = MapperConfig.class)
public interface GlassesMapper {
    @Mapping(target = "categories", ignore = true)
    GlassesResponseDto toDto(Glasses glasses);

    Glasses toModel(GlassesRequestDto requestDto);

    List<GlassesResponseDto> toResponseDtoList(List<Glasses> contactLensesList);

    @AfterMapping
    default void mapCategories(@MappingTarget GlassesResponseDto glassesResponseDto,
                               Glasses glasses) {
        glassesResponseDto.setCategories(glasses.getCategories().stream()
                .map(category -> {
                    CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                    categoryResponseDto.setName(category.getName());
                    return categoryResponseDto;
                })
                .collect(Collectors.toSet()));
    }
}
