package com.ecommerce.shopping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();
    
    public void addItem(CartItem item) {
        this.cartItems.add(item);
        item.setCart(this);
        updateTotalAmount();
    }

    public void removeItem(CartItem item) {
        this.cartItems.remove(item);
        item.setCart(null);
        updateTotalAmount();
    }

    private void updateTotalAmount() {
        this.totalAmount = cartItems.stream().map(cartItem -> {
            BigDecimal unitPrice = cartItem.getUnitPrice();
            if(unitPrice == null) {
                return BigDecimal.ZERO;
            }
            return unitPrice.multiply(new BigDecimal(cartItem.getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clearCart(){
        this.cartItems.clear();
        updateTotalAmount();
    }
}
