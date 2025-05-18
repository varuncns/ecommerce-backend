package com.ecommerce.service;
import com.ecommerce.entity.Product;
import java.util.List;

public interface ProductService {

    Product createProduct(Product request);
    Product updateProduct(Long id, Product request);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
