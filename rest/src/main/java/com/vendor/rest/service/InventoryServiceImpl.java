package com.vendor.rest.service;

import com.vendor.rest.dao.InventoryDao;
import com.vendor.rest.entity.Inventory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryDao inventoryDao;
    public InventoryServiceImpl(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Override
    public List<Inventory> findAll() {
        List<Inventory> inventories = new ArrayList<>();
        for (Inventory d : inventoryDao.findAll()) {
            inventories.add(d);
        }
        if (inventories.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "No items found.");
        }
        return inventories;
    }

    @Override
    public Inventory findById(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(BAD_REQUEST, "ID of Inventory is invalid.");
        }
        return inventoryDao.findById(id).orElseThrow(() ->
                new ResponseStatusException(NOT_FOUND, "Product not found."));

    }
    @Override
    public Inventory save(Inventory inventory) {
        if (inventory == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Inventory cannot be null.");
        }
        if (inventory.getProduct() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Inventories product cannot be empty.");
        }
        return inventoryDao.save(inventory);
    }
    @Override
    public Inventory update(Inventory inventory, Long id) {
        Inventory existingInventory = findById(id);
        if (!Objects.equals(inventory.getProduct().getId(), existingInventory.getProduct().getId())) {
            throw new ResponseStatusException(BAD_REQUEST, "The product type, that you try to load is not correct");
        }
        int actualQuantity = existingInventory.getQuantity();
        int updatedQuantity = inventory.getQuantity() + actualQuantity;
        if (updatedQuantity > 10) {
            throw new ResponseStatusException(BAD_REQUEST, "Can not load more then 10 items.");
        }
        existingInventory.setQuantity(updatedQuantity);
        return inventoryDao.save(existingInventory);
    }
}