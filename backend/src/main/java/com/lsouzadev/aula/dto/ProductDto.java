package com.lsouzadev.aula.dto;

import com.lsouzadev.aula.entity.Category;
import com.lsouzadev.aula.entity.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDto {

    private Long id;

    @NotBlank(message = "Campo obrigatorio")
    @Size(min = 3, max = 80, message = "Nome deve ter entre 3 e 80 caracteres")
    private String name;

    @NotBlank(message = "Campo obrigatorio")
    @Size(min = 10, message = "Descricao deve ter no minimo 10 caracteres")
    private String description;

    @NotNull(message = "Campo obrigatorio")
    @Positive(message = "Preco deve ser positivo")
    private Double price;

    @NotBlank(message = "Campo obrigatorio")
    @Size(max = 255, message = "URL da imagem deve ter no maximo 255 caracteres")
    private String imgUrl;

    @NotNull(message = "Campo obrigatorio")
    private Instant date;

    @Valid
    @NotEmpty(message = "Produto deve ter pelo menos uma categoria")
    private List<CategoryDto> categories = new ArrayList<>();

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public ProductDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();
    }

    public ProductDto(Product entity, Set<Category> categories) {
        this(entity);
        for (Category category : categories) {
            this.categories.add(new CategoryDto(category));
        }
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
