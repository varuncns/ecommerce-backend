package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductPublicDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
	ProductDTO createProduct(ProductDTO dto);
    ProductDTO updateProduct(Long id, ProductDTO dto);
    void deleteProduct(Long id);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();

    // public-facing APIs
    Page<ProductPublicDTO> getAllProducts(Pageable pageable);
    Page<ProductPublicDTO> searchProducts(String keyword, Pageable pageable);
    Page<ProductPublicDTO> getProductsByCategory(String categoryName, Pageable pageable);

    void updateStock(Long productId, int newStock);
}
