package com.ecommerce.service;

import com.ecommerce.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO placeOrder(String userEmail,Long addressId);
    List<OrderDTO> getUserOrders(String userEmail);
    OrderDTO getOrderById(Long orderId, String userEmail);
    void updateOrderStatus(Long orderId, String newStatus);
}
