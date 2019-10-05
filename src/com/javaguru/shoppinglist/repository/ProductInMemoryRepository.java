package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductInMemoryRepository {
    private Long productIdSequence=0L;
    private Map<Long, Product>productRepository=new HashMap<>();
    public Product save(Product product){
        product.setId(productIdSequence++);
        productRepository.put(product.getId(),product);
        return product;
    }
    public Product findProductById(Long id){
        return productRepository.get(id);
    }
}
