package com.vendor.rest.service;

import com.vendor.rest.dao.InventoryDao;
import com.vendor.rest.entity.Inventory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryDao inventoryDao;
    ;

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
    public Inventory update(Inventory inventory, Long product_id) {

        Inventory existingInventory = inventoryDao.findByProductId(product_id);
        int actualQuantity = existingInventory.getQuantity();
        int updatedQuantity = inventory.getQuantity() + actualQuantity;
        if (updatedQuantity > 10) {
            throw new ResponseStatusException(BAD_REQUEST, "Can not load more then 10 items.");
        }
        existingInventory.setQuantity(updatedQuantity);
        return inventoryDao.save(existingInventory);
    }

}
