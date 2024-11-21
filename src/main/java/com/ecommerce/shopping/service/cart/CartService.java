package com.ecommerce.shopping.service.cart;

import com.ecommerce.shopping.model.Cart;
import com.ecommerce.shopping.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class CartService implements ICartService {

    private final CartRepository cartRepository;

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
