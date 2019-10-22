package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.util.NoSuchElementException;

public class ProductService {
    private ProductInMemoryRepository repository;
    private ProductValidationService validationService;

    public ProductService(ProductInMemoryRepository repository,
                          ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    public Long createProduct(Product product) {
        validationService.validate(product);
        Product createdProduct = repository.save(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id).orElseThrow(() -> new NoSuchElementException("Product not found, id:" + id));
    }

    public void deleteProductById(Long id) {
        repository.deleteProductById(id);
    }

    public void changeProductInformation(Long id, Product product) {
        findProductById(id);
        validationService.validate(product);
        repository.changeProductInformation(id, product);

    }
}
