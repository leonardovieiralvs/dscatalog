package com.lsouzadev.aula.repository;

import com.lsouzadev.aula.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
