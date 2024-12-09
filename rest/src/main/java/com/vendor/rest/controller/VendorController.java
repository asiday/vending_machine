package com.vendor.rest.controller;

import com.vendor.rest.entity.Inventory;
import com.vendor.rest.entity.Product;
import com.vendor.rest.service.InventoryService;
import com.vendor.rest.service.ProductService;
import com.vendor.rest.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

//    @PostMapping("/buy/{productId}")
//    public void buyProduct(
//            @Valid @RequestBody Long productId, Double payment) {
//        vendorService.buyProduct();

  //  }
}
