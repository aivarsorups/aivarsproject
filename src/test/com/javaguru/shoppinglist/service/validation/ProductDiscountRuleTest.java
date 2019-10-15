package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductDiscountRuleTest {
    private final BigDecimal MAX_DISCOUNT = new BigDecimal(101);
    private final BigDecimal MIN_DISCOUNT = new BigDecimal(-1);
    private final BigDecimal PRICE_LIMIT_WITHOUT_DISCOUNT = new BigDecimal(19.99);
    private final BigDecimal RANDOM_DISCOUNT=new BigDecimal(Math.random()*(101-1));
    private final BigDecimal PROPER_PRICE= new BigDecimal(200);
    private final BigDecimal PROPER_DISCOUNT=new BigDecimal(5);
    @Spy
    private ProductDiscountRule victim;
    private Product input;

    @Test
    public void shouldThrowProductValidationException() {
        input = product(null);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product discount cant be null");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionWhenDiscountIsNegative() {
        input = product(MIN_DISCOUNT);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Discount cant be negative count");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionWhenDiscountIsBiggerThan100() {
        input = product(MAX_DISCOUNT);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Discount cant be more than 100%");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionWhenDiscountIsNotPossibleDependingOfPrice() {
        input = product1(RANDOM_DISCOUNT,PRICE_LIMIT_WITHOUT_DISCOUNT);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Cant put discount on product if price is lower than 20$");
         verify(victim).checkNotNull(input);
    }
    @Test
    public void shouldValidateSuccess() {
        input = product1(PROPER_DISCOUNT,PROPER_PRICE);
        victim.validate(input);
        verify(victim).checkNotNull(input);

    }

    private Product product(BigDecimal discount) {
        Product product = new Product();
        product.setDiscount(discount);
        return product;
    }

    private Product product1(BigDecimal discount, BigDecimal price) {
        Product product = new Product();
        product.setDiscount(discount);
        product.setPrice(price);
        return product;
    }

}