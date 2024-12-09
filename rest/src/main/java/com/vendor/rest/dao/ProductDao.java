package com.vendor.rest.dao;

import com.vendor.rest.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Long> {
}
