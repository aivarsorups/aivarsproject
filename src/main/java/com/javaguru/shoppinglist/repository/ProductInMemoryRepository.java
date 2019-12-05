package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("inmemorydb")
public class ProductInMemoryRepository implements ProductRepository {
    private Long productIdSequence = 0L;

    private Map<Long, Product> productRepository = new HashMap<>();
    static BigDecimal MAX_PERCENT_FOR_DISCOUNT = new BigDecimal(100);

    private void calculateActualPrice(Long id) {
        BigDecimal actualPrice = productRepository.get(id).getPrice().subtract(productRepository.get(id).getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(productRepository.get(id).getPrice()));
        productRepository.get(id).setActual_price(actualPrice);
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

    public void changeProductInformation(Long id, Product product) {
        product.setId(id);

        productRepository.put(product.getId(), product);
        calculateActualPrice(id);


    }

    public List findAllProductsByCategory(Category category) {
        return productRepository.values().stream()
                .filter(productCategory -> productCategory.getCategory().equals(category)).collect(Collectors.toList());

    }

    public List<Product> findAllProducts() {
        return new ArrayList<>(productRepository.values());
    }

    public boolean existsByName(String name) {
        return productRepository.values().stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

}
