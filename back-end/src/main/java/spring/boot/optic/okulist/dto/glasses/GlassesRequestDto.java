package spring.boot.optic.okulist.dto.glasses;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

import java.util.Set;

@Data
public class GlassesRequestDto {
    private String glassesName;
    @Positive
    private double price;
    private String description;
    @NotNull
    private String identifier;
    @NotNull
    private String color;
    @NotNull
    private String model;
    private String manufacturer;
    private Set<CategoryResponseDto> categories;
}
