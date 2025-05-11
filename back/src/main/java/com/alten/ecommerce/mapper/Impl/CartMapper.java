package com.alten.ecommerce.mapper.Impl;

import com.alten.ecommerce.dtos.CartDto;
import com.alten.ecommerce.dtos.CartItemDto;
import com.alten.ecommerce.entities.Cart;
import com.alten.ecommerce.entities.CartItem;
import com.alten.ecommerce.mapper.Impl.CartItemMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CartMapper {
    private final CartItemMapper cartItemMapper;

    public CartMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public CartDto toDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());

        if (cart.getUser() != null) {
            dto.setUserId(cart.getUser().getId());
        }

        Set<CartItemDto> itemDtos = new HashSet<>();
        for (CartItem item : cart.getItems()) {
            itemDtos.add(cartItemMapper.toDto(item));
        }
        dto.setItems(itemDtos);

        return dto;
    }

    public Cart toEntity(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        return cart;
    }
}
