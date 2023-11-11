package spring.boot.optic.okulist.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.model.ContactLenses;

@Mapper(config = MapperConfig.class)
public interface ContactLensesMapper {
    @Mapping(target = "categories", ignore = true)
    ContactLensesResponseDto toDto(ContactLenses contactLenses);

    ContactLenses toModel(ContactLensesRequestDto contactLensesRequestDto);

    List<ContactLensesResponseDto> toResponseDtoList(List<ContactLenses> contactLensesList);

    @AfterMapping
    default void mapCategories(@MappingTarget ContactLensesResponseDto contactLensesResponseDto,
                               ContactLenses contactLenses) {
        contactLensesResponseDto.setCategories(contactLenses.getCategories().stream()
                .map(category -> {
                    CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                    categoryResponseDto.setName(category.getName());
                    return categoryResponseDto;
                })
                .collect(Collectors.toSet()));
    }

    default List<String> mapDiopterOptions(List<ContactLenses> contactLensesList) {
        return contactLensesList.stream()
                .map(ContactLenses::getDiopter)
                .distinct()
                .collect(Collectors.toList());
    }
}
