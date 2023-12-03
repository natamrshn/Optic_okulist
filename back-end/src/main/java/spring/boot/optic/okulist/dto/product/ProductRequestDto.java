package spring.boot.optic.okulist.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Set;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String name;
    @Positive
    private double price;
    @NotNull
    private String identifier;
    private String category;
    private Set<Long> categoryIds;
}
