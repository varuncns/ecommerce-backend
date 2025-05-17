package com.ecommerce.service;

import com.ecommerce.entity.Product;
import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();
}
