package com.lsouzadev.aula.controller;

import com.lsouzadev.aula.dto.ProductDto;
import com.lsouzadev.aula.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                          @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                          @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return ResponseEntity.ok(productService.findAllPaged(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> insert(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.insert(productDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.update(id, productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
