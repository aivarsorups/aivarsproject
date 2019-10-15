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
public class ProductPriceValidationRuleTest {
@Spy
private ProductPriceValidationRule victim;

private Product input;
private static BigDecimal negativePrice=new BigDecimal(-1);
private static BigDecimal zeroPrice=BigDecimal.ZERO;
private static BigDecimal price=new BigDecimal(20);
    @Test
    public void shouldThrowProductValidationException() {
        input=product(null);
        assertThatThrownBy(()->victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product price cant be null");
        verify(victim).checkNotNull(input);
    }
    @Test
    public void shouldThrowProductValidationExceptionWhenPriceIsNegative() {
        input=product(negativePrice);
        assertThatThrownBy(()->victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product price cant be zero or negative");
        verify(victim).checkNotNull(input);
    }
    @Test
    public void shouldThrowProductValidationExceptionWhenPriceIsZero() {
        input=product(zeroPrice);
        assertThatThrownBy(()->victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product price cant be zero or negative");
        verify(victim).checkNotNull(input);
    }
    @Test
    public void shouldValidateSuccess() {
        input = product(price);
        victim.validate(input);
        verify(victim).checkNotNull(input);

    }


    private Product product(BigDecimal price){
        Product product=new Product();
        product.setPrice(price);
        return product;
   }

}