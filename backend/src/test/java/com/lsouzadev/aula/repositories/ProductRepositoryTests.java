package com.lsouzadev.aula.repositories;

import com.lsouzadev.aula.entity.Product;
import com.lsouzadev.aula.repository.ProductRepository;
import com.lsouzadev.aula.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

   static final long EXISTING_ID = 1L;
   static final long NON_EXISTING_ID = 100L;
   static final long COUNT_TOTAL_PRODUCTS = 25L;

    @Test
    void deleteShouldDeleteObjectWhenIdExists() {

        productRepository.deleteById(EXISTING_ID);

        Optional<Product> result = productRepository.findById(EXISTING_ID);

        Assertions.assertFalse(result.isPresent());
    }

//    @Test
//    void deleteShouldThrowNotFoundExceptionWhenIdDoesNotExist() {
//
//        Assertions.assertThrows(NotFoundException.class, () -> {
//            productRepository.findById(NON_EXISTING_ID);
//        });
//    }

    @Test
    void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);

        product = productRepository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(COUNT_TOTAL_PRODUCTS + 1, product.getId());
    }

    @Test
    void retonarNaoVazioQuandoIdExistir() {

        Optional<Product> byId = productRepository.findById(EXISTING_ID);
        Assertions.assertTrue(byId.isPresent());
    }

    @Test
    void retornarVazioQuandoIdNaoExistir() {

        Optional<Product> byId = productRepository.findById(NON_EXISTING_ID);
        Assertions.assertFalse(byId.isPresent());
    }
}
