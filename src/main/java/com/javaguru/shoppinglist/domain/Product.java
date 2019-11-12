package com.javaguru.shoppinglist.domain;


import com.javaguru.shoppinglist.Category;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {


    private Long id;
    private String name;
    private BigDecimal price;

    private BigDecimal discount;
    private BigDecimal actualPrice;

    private String description;
    private Category category;

    public Product(String name) {
        this.name = name;
    }

    public Product(BigDecimal price) {
        this.price = price;
    }

    public Product() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(Category category, String name, BigDecimal price, BigDecimal discount, BigDecimal actualPrice, String description) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.actualPrice = actualPrice;
        this.description = description;
        this.category = category;

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

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getDiscount(), product.getDiscount()) &&
                Objects.equals(actualPrice, product.actualPrice) &&
                Objects.equals(getDescription(), product.getDescription());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", actualPrice=" + actualPrice +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getDiscount(), actualPrice, getDescription());
    }
}