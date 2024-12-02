package com.ecommerce.shopping.service.cart;

import com.ecommerce.shopping.model.CartItem;

public interface ICartItemService {
    void addItemtoCart(Long cartId, Long productId, int quantity);
    void removeItemfromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
