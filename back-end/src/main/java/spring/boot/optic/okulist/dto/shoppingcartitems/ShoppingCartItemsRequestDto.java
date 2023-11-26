package spring.boot.optic.okulist.dto.shoppingcartitems;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShoppingCartItemsRequestDto {
    @NotNull
    @Positive
    private Long productId;
    private Long glassesId;
    private Long liquidId;
    private Long contactLensesId;
    @Positive
    private int quantity;
    @NotBlank
    private String productType;
}
