package spring.boot.optic.okulist.dto.glasses;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;
import spring.boot.optic.okulist.model.Glasses;

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
    List<Variation> variations;

    @Data
    @NoArgsConstructor
    public static class Variation {
        private Long id;
        private String coverImage;

        public Variation(Glasses glasses) {
            this.id = glasses.getId();
            this.coverImage = glasses.getCoverImage();
        }
    }
}
