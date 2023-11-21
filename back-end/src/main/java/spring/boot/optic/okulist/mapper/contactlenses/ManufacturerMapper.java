package spring.boot.optic.okulist.mapper.contactlenses;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;

@Mapper(config = MapperConfig.class,
        uses = {ColorMapper.class,
                CylinderMapper.class,
                DegreeMapper.class,
                DiopterMapper.class})
public interface ManufacturerMapper {

    @Mapping(source = "color.id", target = "colorId")
    @Mapping(source = "cylinder.id", target = "cylinderId")
    @Mapping(source = "degree.id", target = "degreeId")
    @Mapping(source = "diopter.id", target = "diopterId")
    @Mapping(source = "sphere.id", target = "sphereId")
    ManufacturerResponseDto toDto(Manufacturer manufacturer);

    Manufacturer toModel(ManufacturerRequestDto manufacturerRequestDto);
}
