package com.ecommerce.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role  implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name; // e.g., ROLE_USER, ROLE_ADMIN
    
    @Override
    public String getAuthority() {
        return name;
    }
}
