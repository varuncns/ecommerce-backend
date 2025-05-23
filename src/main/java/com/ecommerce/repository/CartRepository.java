package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.ecommerce.entity.*;
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
