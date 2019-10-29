package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidationRule {
    private final int MAX_NAME_LENGTH = 32;
    private final int MIN_NAME_LENGTH = 3;


    @Override
    public void validate(Product product) {
        checkNotNull((product));
        if (product.getName() == null) {
            throw new ProductValidationException("Product name must be not null");
        }
        if (product.getName().length() < MIN_NAME_LENGTH) {
            throw new ProductValidationException("Product name must be longer than 3 signs");
        }
        if (product.getName().length() > MAX_NAME_LENGTH) {
            throw new ProductValidationException("Product name cant be longer than 32 signs");
        }

    }
}
