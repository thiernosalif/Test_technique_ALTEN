package com.alten.ecommerce.services;

import com.alten.ecommerce.entities.Product;

import java.util.Set;

public interface WishlistService {

    public void addProductToWishlist(String email, Long productId);

    public Set<Product> getWishlistProducts(String email);
}
