package spring.boot.optic.okulist.service.contactlenses;

import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesSearchParameter;
import spring.boot.optic.okulist.exception.ContactLensesSearchException;
import spring.boot.optic.okulist.mapper.ContactLensesMapper;
import spring.boot.optic.okulist.model.ContactLenses;
import spring.boot.optic.okulist.repository.ContactLensesRepository;
import spring.boot.optic.okulist.specification.contactlenses.builders.ContactLensesSpecificationBuilder;

@Service
@RequiredArgsConstructor
public class ContactLensesServiceImpl implements ContactLensesService {
    private static final Logger logger = LogManager.getLogger(ContactLensesServiceImpl.class);
    private final ContactLensesRepository contactLensesRepository;
    private final ContactLensesMapper contactLensesMapper;
    @NonNull
    private final ContactLensesSpecificationBuilder specificationBuilder;
    private final DiopterOptionsService diopterOptionsService;

    public ContactLensesResponseDto saveContactLenses(ContactLensesRequestDto requestDto) {
        ContactLenses contactLenses = contactLensesMapper.toModel(requestDto);
        ContactLenses savedContactLenses = contactLensesRepository.save(contactLenses);
        return contactLensesMapper.toDto(savedContactLenses);
    }

    public List<ContactLensesResponseDto> findAllContactLenses() {
        List<ContactLenses> allContactLenses = contactLensesRepository.findAll();
        return contactLensesMapper.toResponseDtoList(allContactLenses);
    }

    public List<ContactLensesResponseDto> getContactLensesByName(String name) {
        List<ContactLenses> contactLensesList = contactLensesRepository
                .findByNameAndIsDeletedFalse(name);
        return contactLensesMapper.toResponseDtoList(contactLensesList);
    }

    @Override
    public List<ContactLensesResponseDto> getContactLensesByParameters(
            ContactLensesSearchParameter parameters) {
        try {
            Objects.requireNonNull(specificationBuilder,
                    "specificationBuilder must not be null");
            Objects.requireNonNull(contactLensesRepository,
                    "contactLensesRepository must not be null");
            Objects.requireNonNull(contactLensesMapper,
                    "mapper must not be null");

            List<String> availableDiopterOptions = diopterOptionsService
                    .determineAvailableOptions(parameters.selectedParameter());

            Specification<ContactLenses> specification = specificationBuilder.build(parameters);
            List<ContactLenses> contactLensesList = contactLensesRepository.findAll(specification);

            ContactLensesResponseDto responseDto = new ContactLensesResponseDto();
            responseDto.setAvailableDiopterOptions(availableDiopterOptions);

            return contactLensesList.stream().map(contactLensesMapper::toDto).toList();
        } catch (Exception e) {
            logger.error("An error occurred during contact lenses search.", e);
            throw new ContactLensesSearchException("Error during contact lenses search", e);
        }
    }
}

/*
Основний функціонал:
В методі перевіряється, щоб не було null для важливих компонентів,
таких як specificationBuilder, contactLensesRepository та mapper.
За допомогою diopterOptionsService визначаються доступні параметри
для вибраного параметра. Наприклад, якщо параметром є "діоптр",
то отримається список доступних діоптрів.

За допомогою specificationBuilder створюється специфікація
для пошуку контактних лінз на основі переданих параметрів.

Запит до репозиторію (contactLensesRepository)
 для отримання списку контактних лінз, які задовольняють вказані умови.

Для кожного знайденого об'єкта ContactLenses викликається метод mapper::toDto,
який конвертує його в об'єкт ContactLensesResponseDto.

Створюється об'єкт ContactLensesResponseDto із заповненим списком доступних
параметрів (у даному випадку, діоптрів).

Всі отримані DTO додаються до списку і повертаються користувачеві.
 */
