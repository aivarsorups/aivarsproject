package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationRuleTest {
    @Spy
    private ProductNameValidationRule victim;
    private Product input;

    @Test
    public void shouldThrowProductValidationExceptionWhenNameIsNull() {
        input = product(null);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be not null");
        verify(victim).checkNotNull(input);

    }

    @Test
    public void shouldThrowProductValidationExceptionWhenNameLengthIsSmallerThan3sign() {
        input = product("aa");
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be longer than 3 signs");
        verify(victim).checkNotNull(input);
    }
    @Test
    public void shouldThrowProductValidationExceptionWhenNameLengthIsLongerThan32sign() {
        input = product("12345678910111213141516171819202122232425262728");
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name cant be longer than 32 signs");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldValidateSuccess() {
        input = product("good name");
        victim.validate(input);
        verify(victim).checkNotNull(input);

    }

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

}