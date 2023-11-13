package spring.boot.optic.okulist.mapper;

import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.model.Category;
import spring.boot.optic.okulist.model.Glasses;

@Mapper(config = MapperConfig.class)
public interface GlassesMapper {
    @Mapping(target = "categories", ignore = true)
    GlassesResponseDto toDto(Glasses glasses);

    @Mapping(target = "name", source = "glassesName")
    Glasses toModel(GlassesRequestDto requestDto);

    @AfterMapping
    default void mapCategories(@MappingTarget GlassesResponseDto glassesResponseDto,
                               Glasses glasses) {
        glassesResponseDto.setName(glasses.getName());
        glassesResponseDto.setCategories(glasses.getCategories().stream()
                .map(category -> {
                    CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                    categoryResponseDto.setId(category.getId());
                    categoryResponseDto.setName(category.getName());
                    return categoryResponseDto;
                })
                .collect(Collectors.toSet()));
    }

    default CategoryResponseDto mapCategoryToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }
}
