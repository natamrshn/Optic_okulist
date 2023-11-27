package spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses;

import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class ContactLensesResponseDto {
    private Long id;
    private Long lensConfigurationId;
    private Set<CategoryResponseDto> categories;
    private String name;
    private double price;
    private String identifier;
    private String description;
}
