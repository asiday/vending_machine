package com.vendor.rest.controller;

import com.vendor.rest.entity.Product;
import com.vendor.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing products.
 * Provides endpoints for CRUD operations on the {@link Product} entity.
 */

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products.
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return (List<Product>) productService.findAll();
    }

}
