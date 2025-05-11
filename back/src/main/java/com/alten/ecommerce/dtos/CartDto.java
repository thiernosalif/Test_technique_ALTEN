package com.alten.ecommerce.dtos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private Long userId;
    private Set<CartItemDto> items = new HashSet<>();

}
