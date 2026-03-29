package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;

public interface ProductService {

    boolean saveProduct(Product product);

    List<Product> getAllProducts();

    
    Product getProductById(Long id);

    boolean deleteProduct(Long id);
}
