package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findProductById(Long id);

    boolean existsByName(String name);

    void deleteProductById(Long id);

    List findAllProductsByCategory(Category category);

    List<Product> findAllProducts();

    void changeProductInformation(Long id, Product product);


}
