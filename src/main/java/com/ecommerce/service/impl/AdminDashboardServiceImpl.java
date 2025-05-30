package com.ecommerce.service.impl;

import com.ecommerce.dto.*;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.AdminDashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public LogisticsDashboardDTO getLogisticsSummary() {
        List<Order> orders = orderRepository.findAll();

        long totalOrders = orders.size();
        long totalUsers = userRepository.count();

        Map<String, Long> statusCounts = orders.stream()
                .collect(Collectors.groupingBy(o -> o.getStatus().name(), Collectors.counting()));

        List<SimpleOrderDTO> recentOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .limit(10)
                .map(o -> {
                    String addressSummary;
                    if (o.getAddress() != null) {
                        String line1 = o.getAddress().getStreet() != null ? o.getAddress().getStreet() : "No Street";
                        String city = o.getAddress().getCity() != null ? o.getAddress().getCity() : "No City";
                        addressSummary = line1 + ", " + city;
                    } else {
                        addressSummary = "No Address";
                    }

                    return SimpleOrderDTO.builder()
                        .orderId(o.getId())
                        .userName(o.getUser().getName())
                        .addressSummary(addressSummary)
                        .status(o.getStatus().name())
                        .createdAt(o.getCreatedAt())
                        .build();
                })
                .collect(Collectors.toList());

        return LogisticsDashboardDTO.builder()
                .totalOrders(totalOrders)
                .totalUsers(totalUsers)
                .orderStatusCounts(statusCounts)
                .recentOrders(recentOrders)
                .build();
    }
    
    @Override
    public FinancialInsightsDTO getFinancialInsights() {
        List<Order> orders = orderRepository.findAll();

        BigDecimal totalRevenue = orders.stream()
                .map(Order::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalOrders = orders.size();
        BigDecimal averageOrderValue = totalOrders > 0 ? totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;

        // Map of productId -> quantitySold
        Map<Long, Long> productSales = new HashMap<>();
        for (Order order : orders) {
            for (OrderItem item : order.getItems()) {
                productSales.merge(item.getProduct().getId(), (long) item.getQuantity(), Long::sum);
            }
        }
        
        Map<String, BigDecimal> revenueByStatus = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.getStatus().name(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Order::getTotalPrice,
                                BigDecimal::add
                        )
                ));

        List<TopProductDTO> topProducts = productSales.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(5)
                .map(entry -> {
                    Product product = productRepository.findById(entry.getKey())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    return TopProductDTO.builder()
                            .productId(product.getId())
                            .name(product.getName())
                            .quantitySold(entry.getValue())
                            .build();
                })
                .collect(Collectors.toList());

        return FinancialInsightsDTO.builder()
                .totalRevenue(totalRevenue)
                .totalOrders(totalOrders)
                .averageOrderValue(averageOrderValue)
                .topProducts(topProducts)
                .revenueByStatus(revenueByStatus) 
                .build();
    }

}
