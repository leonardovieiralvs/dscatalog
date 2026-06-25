package com.lsouzadev.aula.services;

import com.lsouzadev.aula.dto.CategoryDto;
import com.lsouzadev.aula.entity.Category;
import com.lsouzadev.aula.exceptions.NotFoundException;
import com.lsouzadev.aula.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll() {
        List<Category> listEntity = categoryRepository.findAll();

        return listEntity.stream().map(x -> new CategoryDto(x)).toList();
    }

    public CategoryDto findById(Long id) {
        Category findById = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        return new CategoryDto(findById);
    }
}
