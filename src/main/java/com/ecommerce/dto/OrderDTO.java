package com.ecommerce.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private String status;
    private List<OrderItemDTO> items;
    private AddressDTO address;
}
