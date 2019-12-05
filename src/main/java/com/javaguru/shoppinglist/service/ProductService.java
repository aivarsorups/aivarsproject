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
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

//import com.javaguru.shoppinglist.service.validation.ProductNameUpdateValidation;

@Service
public class ProductService {
    private static final BigDecimal MAX_PERCENT_FOR_DISCOUNT = new BigDecimal(100);
    private ProductRepository repository;
    private ProductValidationService validationService;
    private void actualPriceCalculate(Product product){
        product.setActual_price(product.getPrice().subtract(product.getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(product.getPrice())));
    }


    @Autowired
    public ProductService(ProductRepository repository,
                          ProductValidationService validationService

    ) {
        this.repository = repository;
        this.validationService = validationService;

    }

    @Transactional
    public Long createProduct(Product product) {
        actualPriceCalculate(product);
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
        actualPriceCalculate(product);
        validationService.validate(product);
        repository.changeProductInformation(id, product);

    }
}
