package com.vendor.rest.service;

import com.vendor.rest.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    Product update(Long id, Product product);
}
