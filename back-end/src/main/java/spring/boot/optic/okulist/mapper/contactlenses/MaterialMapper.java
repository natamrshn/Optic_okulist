package spring.boot.optic.okulist.mapper.contactlenses;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.parameters.material.MaterialRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.material.MaterialResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Material;

@Mapper(config = MapperConfig.class)
public interface MaterialMapper {
    MaterialResponseDto toDto(Material material);

    Material toModel(MaterialRequestDto materialRequestDto);
}
