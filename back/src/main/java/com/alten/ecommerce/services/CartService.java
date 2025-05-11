package com.alten.ecommerce.services;

import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.entities.Product;

import java.util.Set;

public interface CartService {

    public void addProductToCart(String email, ProductDto productDto);

    public Set<ProductDto> getCartProducts(String email);

    public void removeProductFromCart(String email, Long productId);
}
