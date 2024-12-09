package com.vendor.rest.service;

import com.vendor.rest.dao.ProductDao;
import com.vendor.rest.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockitoBean
    private ProductDao productDao;

    @Test
    void shouldFindAllProducts() {
        //given
        Product product1 = new Product();
        product1.setName("Product1");
        product1.setPrice(1.20);
        Product product2 = new Product();
        product2.setName("Product2");
        product2.setPrice(1.20);
        //when
        when(productDao.findAll()).thenReturn(List.of(product1, product2));
        List<Product> result = productService.findAll();
        //then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getName());
        assertEquals("Product2", result.get(1).getName());
        verify(productDao, times(1)).findAll();

    }
}
