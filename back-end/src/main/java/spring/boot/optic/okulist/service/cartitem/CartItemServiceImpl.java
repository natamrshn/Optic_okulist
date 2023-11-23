package spring.boot.optic.okulist.service.cartitem;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.ShoppingCartItemsRequestDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.UpdateQuantityDto;
import spring.boot.optic.okulist.model.ShoppingCartItem;
import spring.boot.optic.okulist.model.User;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    @Override
    public CartItemResponseDto save(ShoppingCartItemsRequestDto cartItemRequestDto) {
        return null;
    }

    @Override
    public Set<CartItemResponseDto> findByShoppingCartId(Long id) {
        return null;
    }

    @Override
    public CartItemResponseDto update(UpdateQuantityDto updateQuantityDto, Long id) {
        return null;
    }

    @Override
    public void setShoppingCartAndCartItems(User user, ShoppingCartItem cartItem) {

    }

    @Override
    public void delete(Long cartItemId) {

    }
}
