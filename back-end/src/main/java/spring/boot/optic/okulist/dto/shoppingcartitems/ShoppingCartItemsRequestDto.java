package spring.boot.optic.okulist.dto.shoppingcartitems;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartItemsRequestDto {
    @NotNull
    @Positive
    private Long productId;
    private Long glassesId;
    private List<Long> contactLensesIds;
    @Positive
    private int quantity;
    @NotBlank
    private String productType;
}
