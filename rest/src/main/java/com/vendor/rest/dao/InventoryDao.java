package com.vendor.rest.dao;

import com.vendor.rest.entity.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryDao extends CrudRepository<Inventory, Long> {
    public Inventory findByProductId(Long productId);
}
