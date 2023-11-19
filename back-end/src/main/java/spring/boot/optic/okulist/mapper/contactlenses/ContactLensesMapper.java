package spring.boot.optic.okulist.mapper.contactlenses;

import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.model.Category;
import spring.boot.optic.okulist.model.lenses.ContactLenses;

@Mapper(config = MapperConfig.class)
public interface ContactLensesMapper {

    ContactLensesResponseDto toDto(ContactLenses contactLenses);

    ContactLenses toModel(ContactLensesRequestDto contactLensesRequestDto);

    @AfterMapping
    default void mapCategories(@MappingTarget ContactLensesResponseDto contactLensesResponseDto,
                               ContactLenses contactLenses) {
        contactLensesResponseDto.setLensConfigurationId(contactLensesResponseDto
                .getLensConfigurationId());
        contactLensesResponseDto.setCategories(contactLenses.getCategories().stream()
                .map(this::mapCategoryToDto)
                .collect(Collectors.toSet()));
    }

    default CategoryResponseDto mapCategoryToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }
}
