package com.ecommerce.service;

public interface EmailService {
    void sendOrderConfirmationToUser(String to, String userName, Long orderId, String total,String appName);
    void sendOrderNotificationToAdmin(String to, String userName, Long orderId, String total,String appName);
    void sendOrderStatusUpdateToUser(String to, String userName, Long orderId, String status,String appName);
    void sendOrderStatusUpdateToAdmin(String to, String userName, Long orderId, String status,String appName);
}

