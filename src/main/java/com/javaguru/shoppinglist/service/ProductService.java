package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNameUpdateValidation;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

//import com.javaguru.shoppinglist.service.validation.ProductNameUpdateValidation;

@Service
public class ProductService {
    private ProductRepository repository;
    private ProductValidationService validationService;
    //private ProductNameUpdateValidation productNameUpdateValidation;

    @Autowired
    public ProductService(ProductRepository repository,
                          ProductValidationService validationService
                          //ProductNameUpdateValidation productNameUpdateValidation
    ) {
        this.repository = repository;
        this.validationService = validationService;
        //this.productNameUpdateValidation = productNameUpdateValidation;
    }

    @Transactional
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

    public void changeProductInformation(Long id, Product product, Product product2) {
        findProductById(id);
        validationService.validate(product);
        repository.changeProductInformation(id, product);

    }
}
