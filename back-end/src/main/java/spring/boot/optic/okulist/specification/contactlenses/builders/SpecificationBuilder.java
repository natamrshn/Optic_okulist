package spring.boot.optic.okulist.specification.contactlenses.builders;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesSearchParameter;

public interface SpecificationBuilder<T> {
    Specification<T> build(ContactLensesSearchParameter searchParametersDto);
}
