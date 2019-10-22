package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductPriceValidationRule implements ProductValidationRule {
    private final BigDecimal MIN_PRICE_LIMIT = new BigDecimal(BigInteger.ZERO);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getPrice() == null) {
            throw new ProductValidationException("Product price cant be null");
        }
        if (product.getPrice().compareTo(MIN_PRICE_LIMIT) <= 0) {
            throw new ProductValidationException("Product price cant be zero or negative");
        }
    }


}
