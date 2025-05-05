package com.alten.ecommerce.services;

import com.alten.ecommerce.entities.Product;

import java.util.Set;

public interface CartService {

    public void addProductToCart(String email, Long productId);

    public Set<Product> getCartProducts(String email);
}
