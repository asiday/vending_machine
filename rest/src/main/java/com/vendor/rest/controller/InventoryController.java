package com.vendor.rest.controller;

import com.vendor.rest.entity.Inventory;
import com.vendor.rest.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Retrieves all items.
     *
     * @return a list of all items in the machine.
     */
    @GetMapping
    public List<Inventory> getAllProducts() {
        return (List<Inventory>) inventoryService.findAll();
    }

    @PutMapping("/{product_id}")
    public Inventory loadProduct(@RequestBody @Valid Inventory inventory,
            @PathVariable Long product_id) {
        return inventoryService.update(inventory, product_id);
    }
}
