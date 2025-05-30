package com.ecommerce.service.impl;

import com.ecommerce.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    private void sendEmail(String to, String subject, String templateName, Context context) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public void sendOrderConfirmationToUser(String to, String userName, Long orderId, String total,String appName) {
        Context context = new Context();
        context.setVariable("appName", appName);
        context.setVariable("userName", userName);
        context.setVariable("orderId", orderId);
        context.setVariable("total", total);
        sendEmail(to, "Order Confirmation", "order-confirmation-user", context);
    }

    @Override
    public void sendOrderNotificationToAdmin(String to, String userName, Long orderId, String total,String appName) {
        Context context = new Context();
        context.setVariable("appName", appName);
        context.setVariable("userName", userName);
        context.setVariable("orderId", orderId);
        context.setVariable("total", total);
        sendEmail(to, "New Order Placed", "order-notification-admin", context);
    }

    @Override
    public void sendOrderStatusUpdateToUser(String to, String userName, Long orderId, String status,String appName) {
        Context context = new Context();
        context.setVariable("appName", appName);
        context.setVariable("userName", userName);
        context.setVariable("orderId", orderId);
        context.setVariable("status", status);
        sendEmail(to, "Order Status Update", "order-status-update-user", context);
    }

    @Override
    public void sendOrderStatusUpdateToAdmin(String to, String userName, Long orderId, String status,String appName) {
        Context context = new Context();
        context.setVariable("appName", appName);
        context.setVariable("userName", userName);
        context.setVariable("orderId", orderId);
        context.setVariable("status", status);
        sendEmail(to, "Order Status Changed", "order-status-update-admin", context);
    }
}
