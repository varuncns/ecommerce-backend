package com.ecommerce.repository;

import com.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // JPA gives us all CRUD methods by default
	Optional<Product> findByProductCode(String productCode);
	Page<Product> findAll(Pageable pageable);

    // Optional: add filters
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Product> findByCategoryNameIgnoreCase(String categoryName, Pageable pageable);

}
