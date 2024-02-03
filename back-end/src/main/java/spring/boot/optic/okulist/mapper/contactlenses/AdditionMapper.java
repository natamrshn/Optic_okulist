package spring.boot.optic.okulist.mapper.contactlenses;

import java.util.List;
import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.parameters.addition.AdditionRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.addition.AdditionResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Addition;

@Mapper(config = MapperConfig.class)
public interface AdditionMapper {

    AdditionResponseDto toDto(Addition addition);

    Addition toModel(AdditionRequestDto additionRequestDto);

    List<AdditionResponseDto> toDtoList(List<Addition> additions);
}
