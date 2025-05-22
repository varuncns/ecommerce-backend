package com.ecommerce.controller;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

//    @GetMapping("/productsAll")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        return ResponseEntity.ok(productService.getAllProducts());
//    }
    
    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product request) {
        return ResponseEntity.ok(productService.updateProduct(request.getId(), request));
    }

    @DeleteMapping("/products")
    public ResponseEntity<Void> deleteProduct(@RequestBody Product request) {
        productService.deleteProduct(request.getId());
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/products")
    public ResponseEntity<Product> getProductById(@RequestBody Product request) {
        return ResponseEntity.ok(productService.getProductById(request.getId()));
    }
    @GetMapping("/productsAll")
    public ResponseEntity<Page<Product>> getAllProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction,
        @RequestParam(required = false) String keyword
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> result = (keyword != null && !keyword.isEmpty()) ?
                productService.searchProducts(keyword, pageable) :
                productService.getAllProducts(pageable);

        return ResponseEntity.ok(result);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> result = productService.searchProducts(keyword, pageable);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/by-category")
    public ResponseEntity<Page<Product>> getProductsByCategory(
        @RequestParam String category,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> result = productService.getProductsByCategory(category, pageable);
        return ResponseEntity.ok(result);
    }

}
