package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if(product.getPrice()==null){
            throw new ProductValidationException("Product price cant be null");
        }if (product.getPrice().compareTo(BigDecimal.ZERO)<=0){
            throw new ProductValidationException("Product price cant be zero or negative");
        }
    }

    @Override
    public void checkNotNull(Product product) {

    }
}
