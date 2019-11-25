package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final BigDecimal MAX_PERCENT_FOR_DISCOUNT = new BigDecimal(100);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product productDTO) {
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setDescription(productDTO.getDescription());
        product.setActualPrice(productDTO.getPrice().subtract(productDTO.getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(productDTO.getPrice())));
        productService.createProduct(product);
        return ResponseEntity.ok(product);


    }

    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        return new ProductDTO(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getDiscount(), product.getActualPrice(), product.getDescription());
    }
}
