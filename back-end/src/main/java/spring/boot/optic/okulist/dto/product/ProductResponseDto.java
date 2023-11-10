package spring.boot.optic.okulist.dto.product;

import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private double price;
    private String identifier;
    private String description;
    private Set<CategoryResponseDto> categories;
}
