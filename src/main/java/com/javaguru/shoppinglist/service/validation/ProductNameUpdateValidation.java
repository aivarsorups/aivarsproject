package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




public class ProductNameUpdateValidation {
    private final ProductRepository repository;


    public ProductNameUpdateValidation(ProductRepository repository) {
        this.repository = repository;
    }


    public void validate(Product product, Product product2) {



            if ((repository.existsByName(product.getName())&&(!product.getName().equals(product2.getName())))) {
                throw new ProductValidationException("Product name must be unique");
            }}
    }




