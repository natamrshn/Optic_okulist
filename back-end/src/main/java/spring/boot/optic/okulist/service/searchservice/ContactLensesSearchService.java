package spring.boot.optic.okulist.service.searchservice;

import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesSearchParameter;
import spring.boot.optic.okulist.exception.ContactLensesSearchException;
import spring.boot.optic.okulist.mapper.ContactLensesMapper;
import spring.boot.optic.okulist.model.ContactLenses;
import spring.boot.optic.okulist.repository.ContactLensesRepository;
import spring.boot.optic.okulist.specification.contactlenses.builders.ContactLensesSpecificationBuilder;

@Service
@RequiredArgsConstructor
public class ContactLensesSearchService {
    private static final Logger logger = LogManager.getLogger(ContactLensesSearchService.class);
    @NonNull
    private final ContactLensesSpecificationBuilder specificationBuilder;
    private final ContactLensesRepository contactLensesRepository;
    @NonNull
    private final ContactLensesMapper mapper;

    public List<ContactLensesResponseDto> searchContactLenses(
            ContactLensesSearchParameter searchParameters) {
        try {
            Objects.requireNonNull(specificationBuilder,
                    "specificationBuilder"
                    + " must not be null");
            Objects.requireNonNull(contactLensesRepository,
                    "contactLensesRepository"
                    + "must not be null");
            Objects.requireNonNull(mapper, "mapper must not be null");
            Specification<ContactLenses> specification = specificationBuilder
                    .build(searchParameters);
            return contactLensesRepository.findAll(specification)
                    .stream()
                    .map(mapper::toDto)
                    .toList();
        } catch (Exception e) {
            logger.error("An error occurred during contact lenses search.", e);
            throw new ContactLensesSearchException("Error during contact lenses search", e);
        }
    }
}
