package com.ecommerce.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleOrderDTO {
    private Long orderId;
    private String userName;
    private String addressSummary;
    private String status;
    private LocalDateTime createdAt;
}
