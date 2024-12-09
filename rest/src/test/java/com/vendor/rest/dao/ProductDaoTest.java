package com.vendor.rest.dao;

import com.vendor.rest.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestPropertySource(properties = "spring.sql.init.data-locations=classpath:data-test.sql")
@DataJpaTest
class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void shouldCreateHibernateInitialRecords() {
        List<Product> products = (List<Product>) productDao.findAll();
        assertEquals(6, products.size());
    }

    @Test
    public void shouldPersistNewProduct() {
        //given
        Product product = new Product();
        product.setName("Product1");
        product.setPrice(1.20);
        //when
        Product productSaved = productDao.save(product);
        //then
        assertEquals(product.getName(), productSaved.getName());
        assertNotNull(productSaved.getId());
    }
    @Test
    public void shouldThrowErrorWhenNameIsNotUnique() {
        //given
        Product product1 = new Product();
        product1.setName("Product1");
        product1.setPrice(1.20);
        Product product2 = new Product();
        product2.setName("Product1");
        product2.setPrice(1.20);
        //when
        Product productSaved = productDao.save(product1);
        //then
        assertThrows(DataIntegrityViolationException.class, () -> productDao.save(product2));
    }
}
