package com.alten.ecommerce.services.impl;

import com.alten.ecommerce.entities.Cart;
import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void addProductToCart(String email, Long productId) {

        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        Cart cart = cartRepository.findByUserEmail(email).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
        cart.getProducts().add(product);
        cartRepository.save(cart);

    }

    @Override
    public Set<Product> getCartProducts(String email) {
        return cartRepository.findByUserEmail(email)
                .map(Cart::getProducts)
                .orElse(Collections.emptySet());
    }
}
