package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductInMemoryRepository {
    private Long productIdSequence = 0L;
    private Map<Long, Product> productRepository = new HashMap<>();
    private static BigDecimal MAX_PERCENT_FOR_DISCOUNT=new BigDecimal(100);



    public Product save(Product product) {
        product.setId(productIdSequence++);
        productRepository.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findProductById(Long id) {

        BigDecimal actualPrice = productRepository.get(id).getPrice().subtract(productRepository.get(id).getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(productRepository.get(id).getPrice()));
        productRepository.get(id).setActualPrice(actualPrice);
        return Optional.ofNullable(productRepository.get(id));
    }

    public void deleteProductById(Long id) {
        productRepository.remove(id);
    }

    public Product changeProductInformation(Long id, Product product) {
        product.setId(id);
        BigDecimal actualPrice = productRepository.get(id).getPrice().subtract(productRepository.get(id).getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(productRepository.get(id).getPrice()));
        productRepository.get(id).setActualPrice(actualPrice);
        productRepository.put(product.getId(), product);
        return product;

    }

    public boolean existsByName(String name) {
        return productRepository.values().stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

}
