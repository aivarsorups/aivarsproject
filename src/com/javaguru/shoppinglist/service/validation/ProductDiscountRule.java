package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductDiscountRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if(product.getDiscount().compareTo(new BigDecimal(100))>0){
            throw new ProductValidationException("Discount cant be more than 100%");
        }
        if(product.getDiscount().compareTo(BigDecimal.ZERO)<0){
            throw new ProductValidationException("Discount cant be negative count");
        }
        if((product.getPrice().compareTo(new BigDecimal(20))<0)&&(product.getDiscount().compareTo(BigDecimal.ZERO)!=0)){
            throw new ProductValidationException("Cant put discount on product if price is lower than 20$");
        }
    }

    @Override
    public void checkNotNull(Product product) {

    }
}
