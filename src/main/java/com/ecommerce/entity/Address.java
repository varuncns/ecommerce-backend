package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    private String type; // e.g., HOME, WORK, etc.

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
