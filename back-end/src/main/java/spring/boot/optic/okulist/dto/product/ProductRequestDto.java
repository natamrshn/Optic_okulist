package spring.boot.optic.okulist.dto.product;

import jakarta.validation.constraints.AssertTrue;
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
    private String identifier;
    private String category;
    private Set<Long> categoryIds;

    @AssertTrue(message = "Both category and categoryIds cannot be provided simultaneously")
    private boolean isValid() {
        return (category == null || categoryIds == null)
                || (category.isEmpty() || categoryIds.isEmpty());
        /*
        To ensure that the user does not provide both category and categoryIds simultaneously.
         If both fields are not empty or not null, the validation will fail.
         */
    }
}
