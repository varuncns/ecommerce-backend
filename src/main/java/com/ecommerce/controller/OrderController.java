package com.ecommerce.controller;

import com.ecommerce.dto.OrderDTO;
import com.ecommerce.entity.Order;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderDTO> placeOrder(@AuthenticationPrincipal UserDetails userDetails,@RequestParam Long addressId
) {
        OrderDTO order = orderService.placeOrder(userDetails.getUsername(), addressId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderDTO>> getOrderHistory(@AuthenticationPrincipal UserDetails userDetails) {
        List<OrderDTO> orders = orderService.getUserOrders(userDetails.getUsername());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(
            @PathVariable Long orderId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        OrderDTO order = orderService.getOrderById(orderId, userDetails.getUsername());
        return ResponseEntity.ok(order);
    }
    
    @PatchMapping("/status/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status
    ) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status updated to " + status);
    }

}
