package com.lsouzadev.aula.services;

import com.lsouzadev.aula.dto.ProductDto;
import com.lsouzadev.aula.entity.Product;
import com.lsouzadev.aula.exceptions.NotFoundException;
import com.lsouzadev.aula.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAll() {
        List<Product> listEntity = productRepository.findAll();

        return listEntity.stream().map(x -> new ProductDto(x)).toList();
    }

    public ProductDto findById(Long id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        return new ProductDto(entity, entity.getCategories());
    }

    public Page<ProductDto> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = productRepository.findAll(pageRequest);
        return list.map(x -> new ProductDto(x));
    }

    public ProductDto insert(ProductDto productDto) {

        Product entity = new Product();
        entity.setName(productDto.getName());

        Product save = productRepository.save(entity);

        return new ProductDto(save);
    }

    public ProductDto update(Long id, ProductDto categoryDto) {
        Product byId = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));

        byId.setName(categoryDto.getName());
        Product save = productRepository.save(byId);

        return new ProductDto(save);
    }

    public void delete(Long id) {
        Product category = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));

        productRepository.delete(category);
    }
}
