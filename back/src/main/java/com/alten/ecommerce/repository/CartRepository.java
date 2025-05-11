package com.alten.ecommerce.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.alten.ecommerce.entities.Cart;
import com.alten.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserEmail(String email);

    Optional<Cart> findByUser(User user);
}
