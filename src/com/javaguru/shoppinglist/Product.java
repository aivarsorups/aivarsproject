package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;

    private enum Category{FRUITS, VEGETABLES}
    private Category cat;

    private BigDecimal discount;
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {if((name.length()>3)&&(name.length()<32)){
        return name;}else{
        System.out.println("Name is to short");
    return null;}
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        if (price.compareTo(BigDecimal.ZERO) > 0) {
            return price;
        } else return null;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;

    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public BigDecimal getDiscount() {if((discount.compareTo(BigDecimal.ZERO) > 0) && (discount.compareTo(BigDecimal.ONE) < 0)){
        return discount;}else return null;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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
                Objects.equals(getDescription(), product.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getDiscount(), getDescription());
    }
}