package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.shoppingcart.ShoppingCartResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.ShoppingCartItemsRequestDto;
import spring.boot.optic.okulist.dto.shoppingcartitems.UpdateQuantityDto;
import spring.boot.optic.okulist.model.user.TemporaryUser;
import spring.boot.optic.okulist.service.cartitem.CartItemService;
import spring.boot.optic.okulist.service.shoppingcart.ShoppingCartService;
import spring.boot.optic.okulist.service.user.UserService;

@Tag(name = "Shopping Cart Controller management",
        description = "Endpoints for managing products in Shopping Carts")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;
    private final UserService userService;

    @PostMapping
    @Operation(summary = "add new item to a shopping cart")
    public CartItemResponseDto addCartItem(@RequestBody @Valid
                                           ShoppingCartItemsRequestDto cartItemRequestDto) {
        return shoppingCartService.addItem(cartItemRequestDto);
    }

    @GetMapping
    @Operation(summary = "Get shopping cart")
    public ShoppingCartResponseDto getShoppingCart(@CookieValue("sessionId") String sessionId,
                                                   Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            TemporaryUser temporaryUser = userService.createTemporary(sessionId);
            sessionId = temporaryUser.getSessionId();
        }
        return shoppingCartService.getShoppingCart(sessionId);
    }


    @PutMapping("/cart-items/{id}")
    @Operation(summary = "Update a cart item by ID")
    public CartItemResponseDto update(@RequestBody @Valid UpdateQuantityDto updateQuantityDto,
                                      @PathVariable Long id) {
        return cartItemService.update(updateQuantityDto, id);
    }

    @DeleteMapping("/{cartItemId}")
    @Operation(summary = "Delete a cart item by ID")
    public void deleteCartItemById(@PathVariable Long cartItemId) {
        cartItemService.delete(cartItemId);
    }
}
