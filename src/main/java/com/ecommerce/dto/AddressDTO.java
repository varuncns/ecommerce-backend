package com.ecommerce.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String type;
}
