package com.vendor.rest.dao;

import com.vendor.rest.entity.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Data Access Object (DAO) for managing {@link Product} entities.
 * <p>
 * This interface extends the {@link CrudRepository} to provide CRUD operations
 * for the {@link Product} entity. It uses Spring Data JPA to abstract away the
 * boilerplate code for database access.
 */

public interface ProductDao extends CrudRepository<Product, Long> {
}
