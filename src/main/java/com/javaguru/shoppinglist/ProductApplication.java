package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.ConsoleUI;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.*;

import java.util.HashSet;
import java.util.Set;

public class ProductApplication {
    public static void main(String[] args) {
        ProductInMemoryRepository repository = new ProductInMemoryRepository();
        ProductValidationRule productNameValidationRule = new ProductNameValidationRule();
        ProductValidationRule productUniqueNameValidationRule = new ProductUniqueNameValidationRule(repository);
        ProductValidationRule productDiscountRule = new ProductDiscountRule();
        ProductValidationRule productPriceValidationRule = new ProductPriceValidationRule();
        Set<ProductValidationRule> rules = new HashSet<>();
        rules.add(productDiscountRule);
        rules.add(productNameValidationRule);
        rules.add(productPriceValidationRule);
        rules.add(productUniqueNameValidationRule);
        ProductValidationService validationService = new ProductValidationService(rules);
        ProductService productService = new ProductService(repository, validationService);
        ConsoleUI consoleUI = new ConsoleUI(productService);
        consoleUI.execute();
    }
}
