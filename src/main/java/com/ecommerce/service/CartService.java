package com.ecommerce.service;

import com.ecommerce.entity.Cart;

public interface CartService {

    void addProductToCart(String email, Long productId, int quantity);

    Cart getUserCart(String email);

    void removeItemFromCart(String email, Long productId);    
    void removeAllOfProductFromCart(String email, Long productId); 
}
