package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ProductInMemoryRepositoryTest {
    private static final String PRODUCT_NAME = "TEST_NAME";
    private static final String PRODUCT_DESCRIPTION = "TEST_DESCRIPTION";
    private static final long PRODUCT_ID = 0L;
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal(100);
    private static final BigDecimal PRODUCT_DISCOUNT = new BigDecimal(50);
    private ProductInMemoryRepository victim = new ProductInMemoryRepository();
    private Product product = product();
    @Mock
    private ProductInMemoryRepository victim2;

    @Test
    public void shouldSave() {
        Product result = victim.save(product);
        assertThat(result).isEqualTo(expectedProduct());

    }

    @Test
    public void findProductById() {
        victim.save(product);
        Optional<Product> result = victim.findProductById(PRODUCT_ID);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProduct());
    }

    @Test
    public void deleteProductById() {

       victim.deleteProductById(product.getId());

    }

    @Test
    public void changeProductInformation() {
        Product product=new Product();
        victim.save(product);
        Product result=victim.changeProductInformation(0L,product());
        assertThat(result).isEqualTo(expectedProduct());
    }

    @Test
    public void existsByName() {
        victim.save(product);
        boolean result=victim.existsByName(PRODUCT_NAME);
        assertThat(result).isTrue();
    }

    private Product product() {
        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }

    private Product expectedProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }

}