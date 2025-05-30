package com.ecommerce.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPublicDTO {
    private Long id;
    private String productCode;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
}
