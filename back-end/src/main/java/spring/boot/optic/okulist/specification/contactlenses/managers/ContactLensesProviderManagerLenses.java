package spring.boot.optic.okulist.specification.contactlenses.managers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.model.ContactLenses;
import spring.boot.optic.okulist.specification.contactlenses.providers.SpecificationProviderLenses;

@RequiredArgsConstructor
@Component
public class ContactLensesProviderManagerLenses
        implements SpecificationProviderManagerLenses<ContactLenses> {
    private final List<SpecificationProviderLenses<ContactLenses>>
            lensesSpecificationProviderLenses;
    private final Map<String, SpecificationProviderLenses<ContactLenses>> providerCache =
            new ConcurrentHashMap<>();

    @Override
    public SpecificationProviderLenses<ContactLenses> getSpecificationProvider(String key) {
        return providerCache.computeIfAbsent(key, this::findProviderByKey);
    }

    private SpecificationProviderLenses<ContactLenses> findProviderByKey(String key) {
        return Optional.ofNullable(lensesSpecificationProviderLenses)
                .orElseThrow(() -> new RuntimeException("bookSpecificationProviders is null"))
                .stream()
                .filter(p -> p.getKey().equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specifcation provider for key " + key
                ));
    }
}
