package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TopProductDTO {
    private Long productId;
    private String name;
    private long quantitySold;
}
