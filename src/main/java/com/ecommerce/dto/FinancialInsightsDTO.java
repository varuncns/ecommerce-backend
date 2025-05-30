package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class FinancialInsightsDTO {
    private BigDecimal totalRevenue;
    private int totalOrders;
    private BigDecimal averageOrderValue;
    private List<TopProductDTO> topProducts;
    private Map<String, BigDecimal> revenueByStatus;
}
