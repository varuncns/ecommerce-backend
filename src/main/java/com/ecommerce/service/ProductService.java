package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO request);
    ProductDTO updateProduct(Long id, ProductDTO request);
    void deleteProduct(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    Page<ProductDTO> getAllProducts(Pageable pageable);
    Page<ProductDTO> searchProducts(String keyword, Pageable pageable);
    Page<ProductDTO> getProductsByCategory(String categoryName, Pageable pageable);
}
