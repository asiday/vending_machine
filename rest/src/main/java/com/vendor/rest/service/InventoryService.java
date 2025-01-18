package com.vendor.rest.service;

import com.vendor.rest.entity.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> findAll();

    Inventory findById(Long id);

    Inventory update(Inventory inventory, Long id);

    Inventory save(Inventory inventory);
}
