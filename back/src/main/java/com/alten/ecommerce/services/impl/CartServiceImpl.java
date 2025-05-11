package com.alten.ecommerce.services.impl;

import com.alten.ecommerce.Exceptions.ResourceNotFoundException;
import com.alten.ecommerce.dtos.CartDto;
import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.entities.Cart;
import com.alten.ecommerce.entities.CartItem;
import com.alten.ecommerce.entities.Product;
import com.alten.ecommerce.entities.User;
import com.alten.ecommerce.mapper.Impl.CartMapper;
import com.alten.ecommerce.mapper.ProductMapper;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.repository.CartRepository;
import com.alten.ecommerce.services.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public void addProductToCart(String email, ProductDto productDto) {
        if (productDto == null || productDto.getId() == null) {
            throw new IllegalArgumentException("Product DTO or ID cannot be null");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productDto.getId()));

        Cart cart = getOrCreateCart(user);

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + 1),
                        () -> {
                            CartItem newItem = new CartItem(cart, product, 1);
                            cart.getItems().add(newItem);
                        }
                );


        cartRepository.save(cart);
    }




    @Override
    public Set<ProductDto> getCartProducts(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return cartRepository.findByUser(user)
                .map(cart -> cart.getItems().stream()
                        .map(CartItem::getProduct)
                        .map(ProductMapper::toDto)  // Utilisez le mapper existant
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }



    @Override
    public void removeProductFromCart(String email, Long productId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + email));

        boolean removed = cart.getItems().removeIf(
                item -> item.getProduct().getId().equals(productId)
        );

        if (!removed) {
            throw new ResourceNotFoundException("Product not found in cart: " + productId);
        }

        cartRepository.save(cart);
    }

    private Cart getOrCreateCart(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }
}


