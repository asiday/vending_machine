package com.vendor.rest.integration;

import com.vendor.rest.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProductService productService;

    @Test
    void shouldListAllProducts() {
        //TODO: write the test
    }
}
