package spring.boot.optic.okulist.service.contactlenses.configmodelservice;

import java.util.List;
import spring.boot.optic.okulist.dto.contactlenses.configurationbyparams.LensesConfigurationModelReqeustDto;
import spring.boot.optic.okulist.dto.contactlenses.configurationbyparams.LensesConfigurationModelResponseDto;

public interface LensConfigurationModelService {
    LensesConfigurationModelResponseDto createLensConfiguration(
            LensesConfigurationModelReqeustDto requestDto);

    LensesConfigurationModelResponseDto getLensConfigurationById(
            Long configurationId);

    List<LensesConfigurationModelResponseDto> getAllLensConfigurations();

    List<LensesConfigurationModelResponseDto> getLensConfigurationsByManufacturerId(
            Long manufacturerId);

    void deleteLensConfiguration(Long configurationId);
}
