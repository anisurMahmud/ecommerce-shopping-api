package com.ecommerce.shopping.service.cart;

import com.ecommerce.shopping.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public void addItemtoCart(Long cartId, Long productId, int quantity) {

    }

    @Override
    public void removeItemfromCart(Long cartId, Long productId) {

    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {

    }
}
