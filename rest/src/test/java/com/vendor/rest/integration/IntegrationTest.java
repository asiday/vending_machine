package com.vendor.rest.integration;

import com.vendor.rest.dao.InventoryDao;
import com.vendor.rest.dao.ProductDao;
import com.vendor.rest.entity.Inventory;
import com.vendor.rest.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@TestPropertySource(properties = "spring.sql.init.data-locations=classpath:data-test.sql")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private InventoryService inventoryService;

    @Test
    public void shouldCreateHibernateInitialRecords() {
        List<Inventory> products = (List<Inventory>) inventoryService.findAll();
        assertEquals(3, products.size());
    }

}
