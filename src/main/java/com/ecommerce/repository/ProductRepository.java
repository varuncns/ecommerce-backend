package com.ecommerce.repository;

import com.ecommerce.entity.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // JPA gives us all CRUD methods by default
	Optional<Product> findByProductCode(String productCode);
}
