package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ProductService {
    private ProductInMemoryRepository repository;
    private ProductValidationService validationService;

    @Autowired
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

    public List findAllCategories(Category category) {
        return repository.findAllProductsByCategory(category);
    }
    public List<Product>findAll(){
        return repository.findAllProducts();
    }

    public void changeProductInformation(Long id, Product product) {
        findProductById(id);
        validationService.validate(product);
        repository.changeProductInformation(id, product);

    }
}
