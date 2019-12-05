package com.javaguru.shoppinglist.dto;

import com.javaguru.shoppinglist.Category;

import java.math.BigDecimal;

public class ProductDTO {
private Long id;
private String name;
private Category category;
private BigDecimal price;
private BigDecimal discount;
private BigDecimal actual_price;
private String Description;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Category category, BigDecimal price, BigDecimal discount, BigDecimal actual_price, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.actual_price = actual_price;
        Description = description;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getActual_price() {
        return actual_price;
    }

    public void setActual_price(BigDecimal actual_price) {
        this.actual_price = actual_price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
