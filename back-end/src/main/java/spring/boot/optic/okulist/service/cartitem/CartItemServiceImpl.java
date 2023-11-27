package spring.boot.optic.okulist.service.cartitem;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.shoppingcart.ShoppingCartResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.ShoppingCartItemsRequestDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.UpdateQuantityDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.CartItemMapper;
import spring.boot.optic.okulist.mapper.ShoppingCartMapper;
import spring.boot.optic.okulist.model.ShoppingCart;
import spring.boot.optic.okulist.model.ShoppingCartItem;
import spring.boot.optic.okulist.model.User;
import spring.boot.optic.okulist.model.lenses.ContactLenses;
import spring.boot.optic.okulist.repository.GlassesRepository;
import spring.boot.optic.okulist.repository.LiquidRepository;
import spring.boot.optic.okulist.repository.ShoppingCartItemRepository;
import spring.boot.optic.okulist.repository.ShoppingCartRepository;
import spring.boot.optic.okulist.repository.lenses.ContactLensesRepository;
import spring.boot.optic.okulist.service.shoppingcart.ShoppingCartManager;
import spring.boot.optic.okulist.service.user.UserService;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final GlassesRepository glassesRepository;
    private final LiquidRepository liquidRepository;
    private final ContactLensesRepository contactLensesRepository;
    private final ShoppingCartItemRepository cartItemRepository;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartManager manager;

    @Override
    @Transactional
    public CartItemResponseDto save(ShoppingCartItemsRequestDto cartItemRequestDto) {
        ShoppingCartItem cartItem = new ShoppingCartItem();
        User user = userService.getAuthenticated();
        ShoppingCart shoppingCart = getOrCreateShoppingCart(user);
        switch (cartItemRequestDto.getProductType()) {
            case "Glasses":
                cartItem.setGlasses(glassesRepository.getById(cartItemRequestDto.getProductId()));
                break;
            case "Liquid":
                cartItem.setLiquid(liquidRepository.getById(cartItemRequestDto.getProductId()));
                break;
            case "ContactLenses":
                List<ContactLenses> contactLenses = contactLensesRepository
                        .findAllById(cartItemRequestDto.getContactLensesIds());
                cartItem.setContactLenses((ContactLenses) contactLenses);
                break;
            default:
                throw new RuntimeException("Unsupported product type");
        }
        int quantity = cartItemRequestDto.getQuantity();
        if (quantity > 99) {
            throw new IllegalArgumentException("Quantity exceeds the limit of 99 units per item.");
        }
        cartItem.setQuantity(quantity);
        cartItem.setShoppingCart(shoppingCart);
        ShoppingCartItem savedCartItem = cartItemRepository.save(cartItem);
        shoppingCart.getCartItems().add(savedCartItem);
        shoppingCartRepository.save(shoppingCart);
        return cartItemMapper.toDto(savedCartItem);
    }

    private ShoppingCart getOrCreateShoppingCart(User user) {
        return shoppingCartRepository.findByUser(user)
                .orElseGet(() -> manager.registerNewCart(user));
    }

    @Override
    public Set<CartItemResponseDto> findByShoppingCartId(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));
        ShoppingCartResponseDto shoppingCartDto = shoppingCartMapper.toDto(shoppingCart);
        return shoppingCartDto.getCartItems();
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
    public void setShoppingCartAndCartItems(User user, ShoppingCartItem cartItem) {
        ShoppingCart shoppingCart = shoppingCartRepository.getUserById(user.getId())
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
    public void delete(Long cartItemId) {
        cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find cart by ID : "
                        + cartItemId));
        cartItemRepository.deleteById(cartItemId);
    }
}
