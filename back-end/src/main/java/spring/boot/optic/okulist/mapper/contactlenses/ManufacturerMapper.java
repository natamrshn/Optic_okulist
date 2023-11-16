package spring.boot.optic.okulist.mapper.contactlenses;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;

@Mapper(config = MapperConfig.class,
        uses = {ColorMapper.class,
                CylinderMapper.class,
                DegreeMapper.class,
                DiopterMapper.class,
                LensConfigurationModelMapper.class})
public interface ManufacturerMapper {

    ManufacturerResponseDto toDto(Manufacturer manufacturer);

    Manufacturer toModel(ManufacturerRequestDto manufacturerRequestDto);
}
