package com.vendor.rest.service;

import com.vendor.rest.entity.Inventory;

import java.util.List;

public interface InventoryService {
    public List<Inventory> findAll();

    public Inventory update(Inventory inventory, Long product_id);

    public Inventory save(Inventory inventory);
}
