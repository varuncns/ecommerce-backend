package com.ecommerce.controller;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/productsAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
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
}
