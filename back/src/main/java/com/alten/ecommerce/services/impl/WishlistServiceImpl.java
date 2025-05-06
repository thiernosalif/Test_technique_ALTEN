package com.alten.ecommerce.services.impl;

import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.entities.Wishlist;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.repository.WishlistRepository;
import com.alten.ecommerce.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProductToWishlist(String email, Long productId) {

        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        Wishlist wishlist = wishlistRepository.findByUserEmail(email).orElseGet(() -> {
            Wishlist newWishlist = new Wishlist();
            newWishlist.setUser(user);
            return wishlistRepository.save(newWishlist);
        });
        wishlist.getProducts().add(product);
        wishlistRepository.save(wishlist);

    }

    @Override
    public Set<Product> getWishlistProducts(String email) {
        return wishlistRepository.findByUserEmail(email)
                .map(Wishlist::getProducts)
                .orElse(Collections.emptySet());
    }
}
