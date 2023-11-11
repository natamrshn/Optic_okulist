package spring.boot.optic.okulist.specification.contactlenses.managers;

import spring.boot.optic.okulist.specification.contactlenses.providers.SpecificationProvider;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getSpecificationProvider(String key);
}
