package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductInMemoryRepository repository;
    @Mock
    private ProductValidationService validationService;
    @InjectMocks
    private ProductService victim;
    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    public void shouldCreateProduct() {
        Product product = product();
        when(repository.save(product)).thenReturn(product);
        Long result = victim.createProduct(product);
        verify(validationService).validate(productCaptor.capture());
        Product captorResult = productCaptor.getValue();
        assertThat(captorResult).isEqualTo(product);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductById() {
        when(repository.findProductById(100L)).thenReturn(Optional.ofNullable(product()));
        Product result = victim.findProductById(100L);
        assertThat(result).isEqualTo(product());
    }

    @Test
    public void shouldThrowExceptionProductNotFound() {
        when(repository.findProductById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> victim.findProductById(100L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found, id:100");
    }

    @Test
    public void deleteProductById() {
        Product product = new Product();
        repository.save(product());
        repository.deleteProductById(100L);



    }

    @Test
    public void changeProductInformation() {
    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setPrice(new BigDecimal(25));
        product.setDiscount(new BigDecimal(20));
        product.setId(100L);
        return product;
    }

}