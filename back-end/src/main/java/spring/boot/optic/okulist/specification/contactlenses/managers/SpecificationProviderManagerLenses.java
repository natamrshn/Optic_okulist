package spring.boot.optic.okulist.specification.contactlenses.managers;

import spring.boot.optic.okulist.specification.contactlenses.providers.SpecificationProviderLenses;

public interface SpecificationProviderManagerLenses<T> {
    SpecificationProviderLenses<T> getSpecificationProvider(String key);
}
