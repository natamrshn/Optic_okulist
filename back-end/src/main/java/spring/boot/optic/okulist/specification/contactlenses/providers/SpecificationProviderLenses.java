package spring.boot.optic.okulist.specification.contactlenses.providers;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProviderLenses<T> {
    String getKey();

    Specification<T> getContactLensesSpecification(String[] params);
}
