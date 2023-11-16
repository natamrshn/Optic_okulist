package spring.boot.optic.okulist.mapper.contactlenses;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.contactlenses.configurationbyparams.LensesConfigurationModelReqeustDto;
import spring.boot.optic.okulist.dto.contactlenses.configurationbyparams.LensesConfigurationModelResponseDto;
import spring.boot.optic.okulist.model.lenses.parameters.LensConfigurationModel;

@Mapper(config = MapperConfig.class,
        uses = {ColorMapper.class,
                CylinderMapper.class,
                DegreeMapper.class,
                DiopterMapper.class,
                MaterialMapper.class,
                SphereMapper.class})
public interface LensConfigurationModelMapper {
    LensesConfigurationModelResponseDto toDto(LensConfigurationModel lensConfigurationModel);

    LensConfigurationModel toModel(LensesConfigurationModelReqeustDto
                                           lensesConfigurationModelReqeustDto);

    LensConfigurationModel toModelConfig(LensesConfigurationModelResponseDto configurationDto);
}

