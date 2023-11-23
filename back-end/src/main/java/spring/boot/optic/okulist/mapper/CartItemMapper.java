package spring.boot.optic.okulist.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.shoppingcartitems.CartItemResponseDto;
import spring.boot.optic.okulist.model.ShoppingCartItem;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {

    CartItemResponseDto toDto(ShoppingCartItem cartItem);

    @AfterMapping
    default void setProductId(@MappingTarget CartItemResponseDto cartItemResponseDto,
                              ShoppingCartItem cartItem) {
        if (cartItem.getGlasses() != null) {
            cartItemResponseDto.getProduct().setId(cartItem.getGlasses().getId());
        } else if (cartItem.getContactLenses() != null) {
            cartItemResponseDto.getProduct().setId(cartItem.getContactLenses().getId());
        } else if (cartItem.getLiquid() != null) {
            cartItemResponseDto.getProduct().setId(cartItem.getLiquid().getId());
        }
    }

    @AfterMapping
    default void setProductName(@MappingTarget CartItemResponseDto cartItemResponseDto,
                                ShoppingCartItem cartItem) {
        if (cartItem.getGlasses() != null) {
            cartItemResponseDto.getProduct().setName(cartItem.getGlasses().getName());
        } else if (cartItem.getContactLenses() != null) {
            cartItemResponseDto.getProduct().setName(cartItem.getContactLenses().getName());
        } else if (cartItem.getLiquid() != null) {
            cartItemResponseDto.getProduct().setName(cartItem.getLiquid().getName());
        }
    }
}
