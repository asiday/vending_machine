package com.vendor.rest.service;

import com.vendor.rest.dao.InventoryDao;
import com.vendor.rest.entity.Inventory;
import com.vendor.rest.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestPropertySource(properties = "spring.sql.init.data-locations=classpath:data-test.sql")
@SpringBootTest
class InventoryServiceTest {
    @Autowired
    private InventoryService inventoryService;

    @MockitoBean
    private InventoryDao inventoryDao;

    @Test
    void testUpdate_SuccessfulUpdate() {
        //given
        Long productId = 101L;
        Inventory existingInventory = new Inventory();
        existingInventory.setId(1L);
        existingInventory.setQuantity(5);
        Product product = new Product();
        product.setId(productId);
        existingInventory.setProduct(product);

        Inventory incomingInventory = new Inventory();
        incomingInventory.setQuantity(3);
        incomingInventory.setProduct(product);

        when(inventoryDao.findByProductId(productId)).thenReturn(existingInventory);
        when(inventoryDao.save(existingInventory)).thenReturn(existingInventory);

        //when
        Inventory updatedInventory = inventoryService.update(incomingInventory, productId);

        //then
        assertNotNull(updatedInventory);
        assertEquals(8, updatedInventory.getQuantity()); // 5 + 3 = 8
        verify(inventoryDao, times(1)).findByProductId(productId);
        verify(inventoryDao, times(1)).save(existingInventory);
    }

    @Test
    void testUpdate_QuantityExceedsLimit() {
        // Arrange
        Long productId = 101L;
        Inventory existingInventory = new Inventory();
        existingInventory.setId(1L);
        existingInventory.setQuantity(9);
        Product product = new Product();
        product.setId(productId);
        existingInventory.setProduct(product);

        Inventory incomingInventory = new Inventory();
        incomingInventory.setQuantity(2); // This will push total quantity to 11
        incomingInventory.setProduct(product);

        when(inventoryDao.findByProductId(productId)).thenReturn(existingInventory);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            inventoryService.update(incomingInventory, productId);
        });

        assertEquals("Can not load more then 10 items.", exception.getReason());
        verify(inventoryDao, times(1)).findByProductId(productId);
        verify(inventoryDao, never()).save(any());
    }

}
