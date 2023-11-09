package spring.boot.optic.okulist.dto.liquid;

import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class LiquidResponseDto {
    private Long id;
    private String name;
    private double price;
    private String identifier;
    private int volume;
    private Set<CategoryResponseDto> categories;
}
