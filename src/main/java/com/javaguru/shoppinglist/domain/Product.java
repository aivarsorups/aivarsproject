package com.javaguru.shoppinglist.domain;


import com.javaguru.shoppinglist.Category;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products2")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique =true, nullable = false)
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "actual_price")
    private BigDecimal actual_price;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
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

    public Product(Category category, String name, BigDecimal price, BigDecimal discount, BigDecimal actual_price, String description) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.actual_price = actual_price;
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

    public void setActual_price(BigDecimal actual_price) {
        this.actual_price = actual_price;
    }

    public BigDecimal getActual_price() {
        return actual_price;
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
                Objects.equals(actual_price, product.actual_price) &&
                Objects.equals(getDescription(), product.getDescription());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", actualPrice=" + actual_price +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getDiscount(), actual_price, getDescription());
    }
}