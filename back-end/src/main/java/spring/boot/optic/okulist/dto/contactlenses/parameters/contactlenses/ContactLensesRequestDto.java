package spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses;

import java.util.Set;
import lombok.Data;
import spring.boot.optic.okulist.dto.category.CategoryResponseDto;

@Data
public class ContactLensesRequestDto {
    private Long lensConfigurationId; // Ідентифікатор конфігурації лінз
    private Set<CategoryResponseDto> categories;
}
