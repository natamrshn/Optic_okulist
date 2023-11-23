package spring.boot.optic.okulist.service.shoppingCart;

import spring.boot.optic.okulist.dto.ShoppingCartItems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.ShoppingCartItems.ShoppingCartItemsRequestDto;
import spring.boot.optic.okulist.dto.shoppingCart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    CartItemResponseDto save(ShoppingCartItemsRequestDto requestDto);

    ShoppingCartResponseDto getShoppingCart();
}
