package spring.boot.optic.okulist.service.cartitem;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.LenseConfigDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.ShoppingCartItemsRequestDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.UpdateQuantityDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.CartItemMapper;
import spring.boot.optic.okulist.model.LenseItemConfig;
import spring.boot.optic.okulist.model.Product;
import spring.boot.optic.okulist.model.ShoppingCart;
import spring.boot.optic.okulist.model.ShoppingCartItem;
import spring.boot.optic.okulist.model.user.User;
import spring.boot.optic.okulist.repository.ProductRepository;
import spring.boot.optic.okulist.repository.ShoppingCartItemRepository;
import spring.boot.optic.okulist.repository.ShoppingCartRepository;
import spring.boot.optic.okulist.service.shoppingcart.ShoppingCartManager;
import spring.boot.optic.okulist.service.user.UserService;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartManager manager;

    @Override
    @Transactional
    public CartItemResponseDto save(ShoppingCartItemsRequestDto cartItemRequestDto) {
        ShoppingCartItem cartItem = createShoppingCartItem(cartItemRequestDto);
        User user = getUser(cartItemRequestDto.getSessionId());
        setShoppingCartAndCartItems(user, cartItem);
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    private ShoppingCartItem createShoppingCartItem(ShoppingCartItemsRequestDto cartItemRequestDto) {
        validateCartItemRequestDto(cartItemRequestDto);

        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setProduct(fetchProduct(cartItemRequestDto.getProductId()));
        cartItem.setQuantity(cartItemRequestDto.getQuantity());
        LenseItemConfig lenseConfig = mapLenseConfig(cartItemRequestDto.getLenseConfig());
        cartItem.setLenseConfig(lenseConfig);
        return cartItem;
    }

    private void validateCartItemRequestDto(ShoppingCartItemsRequestDto cartItemRequestDto) {
        Objects.requireNonNull(cartItemRequestDto, "cartItemRequestDto must not be null");
        Objects.requireNonNull(cartItemRequestDto.getProductId(), "Product ID must not be null");
    }

    private Product fetchProduct(Long productId) {
        try {
            return productRepository.getById(productId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching product details", e);
        }
    }

    private LenseItemConfig mapLenseConfig(LenseConfigDto lenseConfigDto) {
        try {
            return cartItemMapper.toLenseItemConfig(lenseConfigDto);
        } catch (Exception e) {
            throw new RuntimeException("Error mapping lens configuration", e);
        }
    }

    private User getUser(String sessionId) {
        return userService.getUserOrCreateNew(sessionId);
    }

    @Override
    public Set<CartItemResponseDto> findByShoppingCartId(Long id) {
        return cartItemRepository.findCartItemByShoppingCartId(id).stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void setShoppingCartAndCartItems(User user, ShoppingCartItem cartItem) {
        ShoppingCart shoppingCart = shoppingCartRepository.getByUserId(user.getId())
                .orElseGet(() -> manager.registerNewCart(user));
        cartItem.setShoppingCart(shoppingCart);
        List<ShoppingCartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        if (shoppingCart.getCartItems().isEmpty()) {
            shoppingCart.setCartItems(cartItems);
        } else {
            shoppingCart.getCartItems().add(cartItem);
        }
    }

    @Override
    public CartItemResponseDto update(UpdateQuantityDto updateQuantityDto, Long id) {
        ShoppingCartItem cartItem = cartItemRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find cart by ID : " + id));
        int newQuantity = updateQuantityDto.getQuantity();
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        cartItem.setQuantity(updateQuantityDto.getQuantity());
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void delete(Long cartItemId) {
        cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find cart by ID : "
                        + cartItemId));
        cartItemRepository.deleteById(cartItemId);
    }
}
