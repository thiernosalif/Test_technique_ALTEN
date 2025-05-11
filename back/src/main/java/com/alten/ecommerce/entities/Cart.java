package com.alten.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "items"})
@EqualsAndHashCode(exclude = {"user", "items"})
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<CartItem> items = new HashSet<>();

    public void addItem(Product product, int quantity) {
        CartItem item = new CartItem(this, product, quantity);
        items.add(item);
        product.getCartItems().add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.getProduct().getCartItems().remove(item);
        item.setCart(null);
        item.setProduct(null);
    }


}
