package com.ecommerce.shopping.service.cart;

import com.ecommerce.shopping.model.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService implements ICartService {

    @Override
    public Cart getCart(Long id) {
        return null;
    }

    @Override
    public void clearCart(Long id) {

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        return null;
    }
}
