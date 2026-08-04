package com.lsouzadev.aula.tests;

import com.lsouzadev.aula.dto.ProductDto;
import com.lsouzadev.aula.entity.Category;
import com.lsouzadev.aula.entity.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(1L, "Monitor", "blablabla", 200.0, "imagem_url", Instant.now());
        product.getCategories().add(new Category(2L, "Eletronico"));
        return product;
    }

    public static ProductDto createProductDto() {
        Product product = createProduct();
        return new ProductDto(product, product.getCategories());
    }
}
