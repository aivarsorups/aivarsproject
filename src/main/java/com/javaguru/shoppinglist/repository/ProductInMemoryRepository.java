package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductInMemoryRepository {
    private Long productIdSequence = 0L;
    private Map<Long, Product> productRepository = new HashMap<>();
    private static BigDecimal MAX_PERCENT_FOR_DISCOUNT = new BigDecimal(100);

    public void calculateActualPrice(Long id) {
        BigDecimal actualPrice = productRepository.get(id).getPrice().subtract(productRepository.get(id).getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(productRepository.get(id).getPrice()));
        productRepository.get(id).setActualPrice(actualPrice);
    }

    public Product save(Product product) {
        product.setId(productIdSequence++);
        productRepository.put(product.getId(), product);
        calculateActualPrice(product.getId());
        return product;
    }

    public Optional<Product> findProductById(Long id) {

        calculateActualPrice(id);
        return Optional.ofNullable(productRepository.get(id));
    }

    public void deleteProductById(Long id) {
        productRepository.remove(id);
    }

    public Product changeProductInformation(Long id, Product product) {
        product.setId(id);

        productRepository.put(product.getId(), product);
        calculateActualPrice(id);

        return product;

    }

    public List findAllCategories(Category category) {
        return productRepository.values().stream()
                .filter(productCategory -> productCategory.getCategory().equals(category)).collect(Collectors.toList());

    }
    public List findAllProducts(){
        return  productRepository.values().stream().collect(Collectors.toList());
    }

    public boolean existsByName(String name) {
        return productRepository.values().stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

}
