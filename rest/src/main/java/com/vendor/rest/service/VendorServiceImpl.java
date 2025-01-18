package com.vendor.rest.service;

import com.vendor.rest.dao.ProductDao;
import com.vendor.rest.entity.BuyRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendorServiceImpl implements VendorService {
    private final ProductDao productDao;
    public VendorServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void buyProduct(BuyRequest buyRequest) {

    }

}
