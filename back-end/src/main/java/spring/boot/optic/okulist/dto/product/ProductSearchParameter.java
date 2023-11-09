package spring.boot.optic.okulist.dto.product;

import java.util.Set;

public record ProductSearchParameter(
        String name,
        Double price,
        String identifier,
        Set<Long> categoryIds
) {
}
