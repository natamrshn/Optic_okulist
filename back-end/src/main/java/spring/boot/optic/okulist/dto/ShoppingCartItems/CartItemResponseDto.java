package spring.boot.optic.okulist.dto.ShoppingCartItems;

import lombok.Data;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;

@Data
public class CartItemResponseDto {
    private Long id;
    private ProductResponseDto product;
    private int quantity;
}
