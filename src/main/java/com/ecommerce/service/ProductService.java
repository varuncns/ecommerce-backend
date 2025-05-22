package com.ecommerce.service;
import com.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {

    Product createProduct(Product request);
    Product updateProduct(Long id, Product request);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Page<Product> getAllProducts(Pageable pageable);
    Page<Product> searchProducts(String keyword, Pageable pageable);
    Page<Product> getProductsByCategory(String categoryName, Pageable pageable);

}
