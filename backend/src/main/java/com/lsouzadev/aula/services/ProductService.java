package com.lsouzadev.aula.services;

import com.lsouzadev.aula.dto.CategoryDto;
import com.lsouzadev.aula.dto.ProductDto;
import com.lsouzadev.aula.entity.Category;
import com.lsouzadev.aula.entity.Product;
import com.lsouzadev.aula.exceptions.NotFoundException;
import com.lsouzadev.aula.repository.CategoryRepository;
import com.lsouzadev.aula.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDto> findAll() {
        List<Product> listEntity = productRepository.findAll();

        return listEntity.stream().map(x -> new ProductDto(x)).toList();
    }

    public ProductDto findById(Long id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        return new ProductDto(entity, entity.getCategories());
    }

    public Page<ProductDto> findAllPaged(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(x -> new ProductDto(x));
    }

    public ProductDto insert(ProductDto productDto) {

        Product entity = new Product();
        copyDtoToEntity(entity, productDto);

        Product save = productRepository.save(entity);
        return new ProductDto(save);

    }

    public ProductDto update(Long id, ProductDto productDto) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        copyDtoToEntity(entity, productDto);

        Product save = productRepository.save(entity) ;
        return new ProductDto(save);

    }

    public void delete(Long id) {
        Product category = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));

        productRepository.delete(category);
    }

    public void copyDtoToEntity(Product entity, ProductDto productDto) {

        entity.setName(productDto.getName());
        entity.setDescription(productDto.getDescription());
        entity.setPrice(productDto.getPrice());
        entity.setImgUrl(productDto.getImgUrl());
        entity.setDate(productDto.getDate());

        for (CategoryDto cat : productDto.getCategories()) {
            Category category = categoryRepository.getReferenceById(cat.getId());
            entity.getCategories().add(category);
        }
    }
}
