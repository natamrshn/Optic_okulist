package spring.boot.optic.okulist.dto.glasses;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class GlassesResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String identifier;
    private String color;
    private String model;
    private String manufacturer;
    private String imageUrl;
    private String imageUrlSecond;
    private Set<CategoryResponseDto> categories;
}
