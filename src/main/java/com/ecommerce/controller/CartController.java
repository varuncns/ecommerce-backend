package com.ecommerce.controller;

import com.ecommerce.entity.Cart;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity
    ) {
        cartService.addProductToCart(userDetails.getUsername(), productId, quantity);
        return ResponseEntity.ok("Product added to cart");
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.getUserCart(userDetails.getUsername()));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeOneFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        cartService.removeItemFromCart(userDetails.getUsername(), productId);
        return ResponseEntity.ok("One quantity removed from cart");
    }

    @DeleteMapping("/remove-all/{productId}")
    public ResponseEntity<String> removeAllFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        cartService.removeAllOfProductFromCart(userDetails.getUsername(), productId);
        return ResponseEntity.ok("Product completely removed from cart");
    }

}
