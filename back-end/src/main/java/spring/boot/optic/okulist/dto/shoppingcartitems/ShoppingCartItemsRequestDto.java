package spring.boot.optic.okulist.dto.shoppingcartitems;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShoppingCartItemsRequestDto {
    @NotNull
    @Positive
    private Long productId;
    @Positive
    private int quantity;
}
