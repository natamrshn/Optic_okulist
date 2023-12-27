package spring.boot.optic.okulist.dto.liquid;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.model.Liquid;

@Data
public class LiquidResponseDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String identifier;
    private int volume;
    private String imageUrl;
    private String imageUrlSecond;
    private Set<CategoryResponseDto> categories;
    private List<Variations> variations;

    @Data
    @NoArgsConstructor
    public static class Variations {
        private Long id;
        private String coverImage;
    }
}
