package com.trofimov.shop.dtos;

public class ProductDto {
    private Integer id;
    private String name;
    private Float price;
    private CategoryDto category;

    public ProductDto(Integer id, String name, Float price, CategoryDto category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
