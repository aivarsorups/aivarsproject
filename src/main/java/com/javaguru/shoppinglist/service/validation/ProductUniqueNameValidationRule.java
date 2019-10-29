package com.javaguru.shoppinglist.service.validation;


import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUniqueNameValidationRule implements ProductValidationRule {
    private final ProductInMemoryRepository repository;

    @Autowired
    public ProductUniqueNameValidationRule(ProductInMemoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (repository.existsByName(product.getName())) {
            throw new ProductValidationException("Product name must be unique");
        }

    }


}
