package com.vendor.rest.service;

import com.vendor.rest.dao.ProductDao;
import com.vendor.rest.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        for (Product d : productDao.findAll()) {
            products.add(d);
        }
        if (products.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "No product found.");
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(BAD_REQUEST, "ID of Product is invalid.");
        }
        return productDao.findById(id).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Product not found."));
    }

    @Override
    public Product save(Product product) {
        if (product == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Product cannot be null.");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Product name cannot be empty.");
        }
        return productDao.save(product);

    }
    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(BAD_REQUEST, "ID of Product is invalid.");
        }
        if (!productDao.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Product not found.");
        }
        productDao.deleteById(id);
    }
    @Override
    public Product update(Long id, Product newProduct) {
        Product product = findById(id);
        product.setName(newProduct.getName());
        return  save(product);
    }
}
