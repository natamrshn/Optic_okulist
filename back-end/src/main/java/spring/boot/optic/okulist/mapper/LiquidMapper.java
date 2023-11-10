package spring.boot.optic.okulist.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.liquid.LiquidRequestDto;
import spring.boot.optic.okulist.dto.liquid.LiquidResponseDto;
import spring.boot.optic.okulist.model.Liquid;

@Mapper(config = MapperConfig.class)
public interface LiquidMapper {
    @Mapping(target = "categories", ignore = true)
    LiquidResponseDto toDto(Liquid liquid);

    Liquid toModel(LiquidRequestDto liquidRequestDto);

    List<LiquidResponseDto> toResponseDtoList(List<Liquid> contactLensesList);

    @AfterMapping
    default void mapCategories(@MappingTarget LiquidResponseDto liquidResponseDto, Liquid liquid) {
        liquidResponseDto.setCategories(liquid.getCategories().stream()
                .map(category -> {
                    CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                    categoryResponseDto.setName(category.getName());
                    return categoryResponseDto;
                })
                .collect(Collectors.toSet()));
    }

}
