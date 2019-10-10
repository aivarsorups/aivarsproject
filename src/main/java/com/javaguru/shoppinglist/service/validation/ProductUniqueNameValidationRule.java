package com.javaguru.shoppinglist.service.validation;


import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

public class ProductUniqueNameValidationRule implements ProductValidationRule {
    private final ProductInMemoryRepository repository;

    public ProductUniqueNameValidationRule(ProductInMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void checkNotNull(Product product) {

    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (repository.existsByName(product.getName())) {
            throw new ProductValidationException("Product name must be unique");
        }

    }


}
