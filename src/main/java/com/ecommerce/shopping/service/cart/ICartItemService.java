package com.ecommerce.shopping.service.cart;

public interface ICartItemService {
    void addItemtoCart(Long cartId, Long productId, int quantity);
    void removeItemfromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);
}
