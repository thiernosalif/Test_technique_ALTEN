package com.alten.ecommerce.services.impl;

import com.alten.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserEmail(String email);
}
