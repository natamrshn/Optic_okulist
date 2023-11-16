package spring.boot.optic.okulist.service.contactlenses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.configurationbyparams.LensesConfigurationModelResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.mapper.contactlenses.ContactLensesMapper;
import spring.boot.optic.okulist.mapper.contactlenses.LensConfigurationModelMapper;
import spring.boot.optic.okulist.model.lenses.ContactLenses;
import spring.boot.optic.okulist.model.lenses.parameters.LensConfigurationModel;
import spring.boot.optic.okulist.repository.lenses.ContactLensesRepository;
import spring.boot.optic.okulist.service.contactlenses.configmodelservice.LensConfigurationModelService;

@Service
@RequiredArgsConstructor
public class ContactLensesServiceImpl implements ContactLensesService {

    private final ContactLensesRepository contactLensesRepository;
    private final ContactLensesMapper contactLensesMapper;
    private final LensConfigurationModelService lensConfigurationModelService;
    private final LensConfigurationModelMapper configurationModelMapper;

    /*
    це я не тестував і тут тільки один метод
    для створення за конфігурацією
     */

    @Override
    public ContactLensesResponseDto createContactLenses(
            ContactLensesRequestDto contactLensesRequestDto) {
        LensesConfigurationModelResponseDto configurationDto = lensConfigurationModelService
                        .getLensConfigurationById(contactLensesRequestDto.getLensConfigurationId());

        // Мапуємо DTO на модель
        ContactLenses contactLenses = contactLensesMapper.toModel(contactLensesRequestDto);

        // Конвертуємо DTO у модель перед встановленням значення
        LensConfigurationModel lensConfigurationModel = configurationModelMapper
                .toModelConfig(configurationDto);

        // Встановлюємо конфігурацію лінз для лінз
        contactLenses.setLensConfiguration(lensConfigurationModel);

        // Зберігаємо лінзи в базі даних
        ContactLenses savedContactLenses = contactLensesRepository.save(contactLenses);

        // Мапуємо збережені лінзи на DTO і повертаємо їх
        return contactLensesMapper.toDto(savedContactLenses);
    }
}
