package spring.boot.optic.okulist.dto.shoppingcartitems;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShoppingCartItemsRequestDto {
    private Long productId;
    @Positive
    private int quantity;
}
