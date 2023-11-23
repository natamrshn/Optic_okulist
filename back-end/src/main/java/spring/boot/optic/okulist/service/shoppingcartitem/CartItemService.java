package spring.boot.optic.okulist.service.shoppingcartitem;

import spring.boot.optic.okulist.dto.ShoppingCartItems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.ShoppingCartItems.ShoppingCartItemsRequestDto;
import spring.boot.optic.okulist.dto.ShoppingCartItems.UpdateQuantityDto;
import spring.boot.optic.okulist.model.ShoppingCartItem;
import spring.boot.optic.okulist.model.User;

import java.util.Set;

public interface CartItemService {
    CartItemResponseDto save(ShoppingCartItemsRequestDto cartItemRequestDto);

    Set<CartItemResponseDto> findByShoppingCartId(Long id);

    CartItemResponseDto update(UpdateQuantityDto updateQuantityDto, Long id);

    void setShoppingCartAndCartItems(User user, ShoppingCartItem cartItem);

    void delete(Long cartItemId);
}
