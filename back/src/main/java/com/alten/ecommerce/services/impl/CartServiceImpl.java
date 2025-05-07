package com.alten.ecommerce.services.impl;

import com.alten.ecommerce.entities.Cart;
import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.repository.CartRepository;
import com.alten.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {


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

        if (cart.getProducts().contains(product)) {
            throw new RuntimeException("Le produit est déjà dans votre panier");
        }
        cart.getProducts().add(product);
        cartRepository.save(cart);

    }

    @Override
    public Set<Product> getCartProducts(String email) {
        return cartRepository.findByUserEmail(email)
                .map(Cart::getProducts)
                .orElse(Collections.emptySet());
    }

    @Override
    public void removeProductFromCart(String email, Long productId) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        Cart cart = cartRepository.findByUserEmail(email).orElseThrow(() -> new RuntimeException("Panier non trouvé"));

        // Retirer le produit du panier
        cart.getProducts().remove(product);
        cartRepository.save(cart);
    }
}
