package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product>findProductById(Long id);
    boolean existByName(String name);

}
