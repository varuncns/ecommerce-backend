package com.ecommerce.service;

import com.ecommerce.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(String userEmail);
    List<Order> getUserOrders(String userEmail);
    Order getOrderById(Long orderId, String userEmail);
    void updateOrderStatus(Long orderId, String newStatus);

}
