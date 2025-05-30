package com.ecommerce.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsDashboardDTO {
    private long totalOrders;
    private long totalUsers;
    private Map<String, Long> orderStatusCounts;
    private List<SimpleOrderDTO> recentOrders;
}
