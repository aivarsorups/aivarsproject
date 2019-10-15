package com.javaguru.shoppinglist.service.validation;

import static org.junit.Assert.*;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)

public class ProductValidationServiceTest {
    @Mock
    private ProductUniqueNameValidationRule productUniqueNameValidationRule;
    @Mock
    private ProductNameValidationRule productNameValidationRule;
    @Mock
    private ProductPriceValidationRule productPriceValidationRule;
    @Mock
    private ProductDiscountRule productDiscountRule;
    @Captor
    private ArgumentCaptor<Product> captor;
    private ProductValidationService victim;
    private Product product = product();

    @Before
    public void setUp(){
        Set<ProductValidationRule>rules=new HashSet<>();
        rules.add(productUniqueNameValidationRule);
        rules.add(productDiscountRule);
        rules.add(productNameValidationRule);
        rules.add(productPriceValidationRule);
        victim=new ProductValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(product);
        verify(productNameValidationRule).validate(captor.capture());
        verify(productDiscountRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());
        verify(productUniqueNameValidationRule).validate(captor.capture());
        List<Product>resultList=captor.getAllValues();
        assertThat(resultList).containsOnly(product);
    }


    private Product product() {
        Product product = new Product();
        product.setId(123L);
        product.setName("TEST_NAME");
        product.setDiscount(new BigDecimal(15));
        product.setPrice(new BigDecimal(30));
        product.setDescription("TEST_DESCRIPTION");

        return product;
    }
}


