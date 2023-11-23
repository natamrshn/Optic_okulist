package spring.boot.optic.okulist.service.shoppingcart;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.shoppingcart.ShoppingCartResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.ShoppingCartItemsRequestDto;
import spring.boot.optic.okulist.model.ShoppingCart;
import spring.boot.optic.okulist.model.User;
import spring.boot.optic.okulist.repository.ShoppingCartRepository;
import spring.boot.optic.okulist.service.cartitem.CartItemService;
import spring.boot.optic.okulist.service.user.UserService;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartManager registerNewCart;

    @Override
    public CartItemResponseDto save(ShoppingCartItemsRequestDto requestDto) {
        return cartItemService.save(requestDto);
    }

    @Override
    @Transactional
    public ShoppingCartResponseDto getShoppingCart() {
        User authenticatedUser = userService.getAuthenticated();
        ShoppingCart shoppingCart = shoppingCartRepository
                .getUserById(authenticatedUser.getId())
                .orElseGet(() -> registerNewCart.registerNewCart(authenticatedUser));
        Long id = shoppingCart.getId();
        ShoppingCartResponseDto shoppingCartDto = new ShoppingCartResponseDto();
        shoppingCartDto.setId(id);
        shoppingCartDto.setUserId(authenticatedUser.getId());
        shoppingCartDto.setCartItems(cartItemService.findByShoppingCartId(id));
        return shoppingCartDto;
    }
}
