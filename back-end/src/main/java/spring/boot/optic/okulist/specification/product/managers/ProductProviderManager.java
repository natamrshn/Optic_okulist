package spring.boot.optic.okulist.specification.product.managers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.model.Product;
import spring.boot.optic.okulist.specification.glasses.managers.SpecificationProviderManagerGlasses;
import spring.boot.optic.okulist.specification.glasses.providers.SpecificationProviderGlasses;
import spring.boot.optic.okulist.specification.product.providers.SpecificationProviderProduct;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Component
public class ProductProviderManager implements SpecificationProviderManagerProduct<Product> {
    private final List<SpecificationProviderProduct<Product>> productSpecificationProviderProduct;
    private final Map<String, SpecificationProviderProduct<Product>> providerCache =
            new ConcurrentHashMap<>();

    @Override
    public SpecificationProviderProduct<Product> getSpecificationProvider(String key) {
        return providerCache.computeIfAbsent(key, this::findProviderByKey);
    }

    private SpecificationProviderProduct<Product> findProviderByKey(String key) {
        return Optional.ofNullable(productSpecificationProviderProduct)
                .orElseThrow(() -> new RuntimeException("productSpecificationProviders is null"))
                .stream()
                .filter(p -> p.getKey().equalsIgnoreCase(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specifcation provider for key " + key
                ));
    }
}
