package spring.boot.optic.okulist.service.contactlenses.configmodelservice;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.configurationbyparams.LensesConfigurationModelReqeustDto;
import spring.boot.optic.okulist.dto.contactlenses.configurationbyparams.LensesConfigurationModelResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.contactlenses.LensConfigurationModelMapper;
import spring.boot.optic.okulist.model.lenses.parameters.LensConfigurationModel;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;
import spring.boot.optic.okulist.repository.lenses.LensConfigurationModelRepository;
import spring.boot.optic.okulist.repository.lenses.ManufacturerRepository;

@Service
@RequiredArgsConstructor
public class LensConfigurationModelServiceImpl implements LensConfigurationModelService {
    // цей сервіс я ще не тестував тому можливі проблеми з ним
    private final LensConfigurationModelRepository configurationModelRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final LensConfigurationModelMapper configurationModelMapper;

    @Override
    public LensesConfigurationModelResponseDto createLensConfiguration(
            LensesConfigurationModelReqeustDto requestDto) {
        LensConfigurationModel configurationModel = configurationModelMapper
                .toModel(requestDto);
        LensConfigurationModel savedConfigurationModel = configurationModelRepository
                .save(configurationModel);
        return configurationModelMapper.toDto(savedConfigurationModel);
    }

    @Override
    public LensesConfigurationModelResponseDto getLensConfigurationById(Long configurationId) {
        LensConfigurationModel configurationModel = configurationModelRepository
                .findById(configurationId)
                .orElseThrow(() -> new RuntimeException("Lens configuration not found with id: "
                        + configurationId));
        return configurationModelMapper.toDto(configurationModel);
    }

    @Override
    public List<LensesConfigurationModelResponseDto> getAllLensConfigurations() {
        List<LensConfigurationModel> configurationModels = configurationModelRepository.findAll();
        return configurationModels.stream()
                .map(configurationModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LensesConfigurationModelResponseDto> getLensConfigurationsByManufacturerId(
            Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found with id: "
                        + manufacturerId));

        // Отримайте конфігурації лінз для виробника
        List<LensConfigurationModel> configurationModels = manufacturer
                .getLensConfigurationModels();

        // Мапуємо список конфігурацій у список DTO
        return configurationModels.stream()
                .map(configurationModelMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLensConfiguration(Long configurationId) {
        LensConfigurationModel lensConfigurationModel =
                configurationModelRepository.findById(configurationId)
                        .orElseThrow(
                                () -> new EntityNotFoundException("Can't found liquid with ID :"
                                        + configurationId)
                        );
        lensConfigurationModel.setDeleted(true);
        configurationModelRepository.save(lensConfigurationModel);
    }
}
