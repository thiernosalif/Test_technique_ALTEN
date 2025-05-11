package com.alten.ecommerce.mapper.Impl;

import com.alten.ecommerce.dtos.CartItemDto;
import com.alten.ecommerce.entities.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemDto toDto(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setId(cartItem.getId());
        dto.setQuantity(cartItem.getQuantity());

        if (cartItem.getCart() != null) {
            dto.setCartId(cartItem.getCart().getId());
        }

        if (cartItem.getProduct() != null) {
            dto.setProductId(cartItem.getProduct().getId());
        }

        return dto;
    }

    public CartItem toEntity(CartItemDto cartItemDto) {
        CartItem item = new CartItem();
        item.setId(cartItemDto.getId());
        item.setQuantity(cartItemDto.getQuantity());
        return item;
    }

}
