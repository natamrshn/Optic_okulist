package spring.boot.optic.okulist.dto.shoppingCart;

import lombok.Data;
import spring.boot.optic.okulist.dto.ShoppingCartItems.CartItemResponseDto;

import java.util.Set;

@Data
public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    private Set<CartItemResponseDto> cartItems;
}
