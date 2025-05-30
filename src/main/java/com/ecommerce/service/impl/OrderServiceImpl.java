package com.ecommerce.service.impl;

import com.ecommerce.dto.AddressDTO;
import com.ecommerce.dto.OrderDTO;
import com.ecommerce.dto.OrderItemDTO;
import com.ecommerce.entity.*;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.repository.*;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public OrderDTO placeOrder(String email, Long addressId) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // validate address belongs to user
        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized address usage");
        }

        Cart cart = cartRepository.findByUserIdWithItems(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address); // 🔥 set address
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            BigDecimal price = cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(cartItem.getProduct())
                    .quantity(quantity)
                    .priceAtPurchase(price)
                    .build();

            orderItems.add(orderItem);
            total = total.add(price.multiply(BigDecimal.valueOf(quantity)));
        }

        order.setItems(orderItems);
        order.setTotalPrice(total);

        Order saved = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        cartItemRepository.deleteByCartId(cart.getId());

        return toOrderDTO(saved); // with address mapping included
    }


    @Override
    public List<OrderDTO> getUserOrders(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUserId(user.getId())
                .stream()
                .map(this::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long orderId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access to order");
        }

        return toOrderDTO(order);
    }
    
    @Override
    public void updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderStatus currentStatus = order.getStatus();

        OrderStatus newStatusEnum;
        try {
            newStatusEnum = OrderStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid order status: " + newStatus);
        }

        if (!isValidStatusTransition(currentStatus, newStatusEnum)) {
            throw new RuntimeException("Invalid status transition from " + currentStatus + " to " + newStatusEnum);
        }

        order.setStatus(newStatusEnum);
        orderRepository.save(order);
    }
    
    private boolean isValidStatusTransition(OrderStatus from, OrderStatus to) {
        return switch (from) {
            case PENDING -> to == OrderStatus.PAID || to == OrderStatus.CANCELLED;
            case PAID -> to == OrderStatus.SHIPPED || to == OrderStatus.CANCELLED;
            case SHIPPED -> to == OrderStatus.DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
    }
    
    private OrderDTO toOrderDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> OrderItemDTO.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .priceAtPurchase(item.getPriceAtPurchase())
                        .build())
                .collect(Collectors.toList());

        Address address = order.getAddress();
        AddressDTO addressDTO = AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .type(address.getType())
                .build();

        return OrderDTO.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus().name())
                .items(itemDTOs)
                .address(addressDTO)
                .build();
    }
}
