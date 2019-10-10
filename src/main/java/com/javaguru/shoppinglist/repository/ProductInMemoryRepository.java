package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductInMemoryRepository {
    private Long productIdSequence = 0L;
    private Map<Long, Product> productRepository = new HashMap<>();

    public Product save(Product product) {
        product.setId(productIdSequence++);
        productRepository.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(productRepository.get(id));
    }

    public void deleteProductById(Long id) {
        productRepository.remove(id);
    }

    public void changeProductInformation(Long id, Product product) {
        findProductById(id);
        product.setId(id);
        productRepository.put(product.getId(), product);

    }

    public boolean existsByName(String name) {
        return productRepository.values().stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

}
