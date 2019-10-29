package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountRule implements ProductValidationRule {
    private final BigDecimal MAX_DISCOUNT = new BigDecimal(100);
    private final BigDecimal MIN_DISCOUNT = new BigDecimal(0);
    private final BigDecimal PRICE_LIMIT_WITHOUT_DISCOUNT = new BigDecimal(20);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getDiscount() == null) {
            throw new ProductValidationException("Product discount cant be null");
        }
        if (product.getDiscount().compareTo(MAX_DISCOUNT) > 0) {
            throw new ProductValidationException("Discount cant be more than 100%");
        }
        if (product.getDiscount().compareTo(MIN_DISCOUNT) < 0) {
            throw new ProductValidationException("Discount cant be negative count");
        }
        if ((product.getPrice().compareTo(PRICE_LIMIT_WITHOUT_DISCOUNT) < 0) && (product.getDiscount().compareTo(MIN_DISCOUNT) != 0)) {
            throw new ProductValidationException("Cant put discount on product if price is lower than 20$");
        }

    }

}
