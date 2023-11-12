package spring.boot.optic.okulist.dto.liquid;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class LiquidRequestDto {
    @Positive
    private int volume;
    private String name;
    @Positive
    private double price;
    private String description;
    @NotNull
    private String identifier;
    private Set<CategoryResponseDto> categories;
}
