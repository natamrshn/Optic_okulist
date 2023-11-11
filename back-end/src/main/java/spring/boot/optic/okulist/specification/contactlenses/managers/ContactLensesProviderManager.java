package spring.boot.optic.okulist.specification.contactlenses.managers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.model.ContactLenses;
import spring.boot.optic.okulist.specification.contactlenses.providers.SpecificationProvider;

@RequiredArgsConstructor
@Component
public class ContactLensesProviderManager implements SpecificationProviderManager<ContactLenses> {
    private final List<SpecificationProvider<ContactLenses>> bookSpecificationProviders;
    private final Map<String, SpecificationProvider<ContactLenses>> providerCache =
            new ConcurrentHashMap<>();

    @Override
    public SpecificationProvider<ContactLenses> getSpecificationProvider(String key) {
        return providerCache.computeIfAbsent(key, this::findProviderByKey);
    }

    private SpecificationProvider<ContactLenses> findProviderByKey(String key) {
        return Optional.ofNullable(bookSpecificationProviders)
                .orElseThrow(() -> new RuntimeException("bookSpecificationProviders is null"))
                .stream()
                .filter(p -> p.getKey().equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specifcation provider for key " + key
                ));
    }
}
