package com.lsouzadev.aula.repository;

import com.lsouzadev.aula.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
