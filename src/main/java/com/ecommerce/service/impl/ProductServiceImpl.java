package com.ecommerce.service.impl;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductPublicDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Product dtoToEntity(ProductDTO dto) {
        Category category = categoryRepository.findByNameIgnoreCase(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found with name: " + dto.getCategoryName()));

        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .productCode(dto.getProductCode())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(category)
                .build();
    }

    private ProductDTO entityToDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getProductCode(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getName()
        );
    }
    
    private ProductPublicDTO toPublicDto(Product product) {
        return ProductPublicDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .build();
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        if (productRepository.findByProductCode(dto.getProductCode()).isPresent()) {
            throw new RuntimeException("Product with code already exists");
        }
        Product saved = productRepository.save(dtoToEntity(dto));
        return entityToDto(saved);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findByNameIgnoreCase(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setProductCode(dto.getProductCode());
        product.setStock(dto.getStock());
        product.setCategory(category);

        return entityToDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::entityToDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductPublicDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(this::toPublicDto);
    }

    @Override
    public Page<ProductPublicDTO> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable).map(this::toPublicDto);
    }

    @Override
    public Page<ProductPublicDTO> getProductsByCategory(String categoryName, Pageable pageable) {
        return productRepository.findByCategoryNameIgnoreCase(categoryName, pageable).map(this::toPublicDto);
    }
    
    @Override
    public void updateStock(Long productId, int newStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStock(newStock);
        productRepository.save(product);
    }

}
