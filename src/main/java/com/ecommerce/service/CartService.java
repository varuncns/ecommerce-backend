package com.ecommerce.service;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.entity.Cart;

public interface CartService {

    void addProductToCart(String email, Long productId, int quantity);

    CartDTO getUserCart(String email);

    void removeItemFromCart(String email, Long productId);    
    void removeAllOfProductFromCart(String email, Long productId); 
}
