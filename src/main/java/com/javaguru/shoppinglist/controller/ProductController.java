package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final BigDecimal MAX_PERCENT_FOR_DISCOUNT = new BigDecimal(100);
    private final ProductService productService;

    private void actualPriceCalculate(Product product) {
        product.setActual_price(product.getPrice().subtract(product.getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(product.getPrice())));
    }
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/save")
    public ResponseEntity<Product> create(@RequestBody Product productDTO) {
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setDescription(productDTO.getDescription());
        product.setActual_price(productDTO.getPrice().subtract(productDTO.getDiscount().divide(MAX_PERCENT_FOR_DISCOUNT).multiply(productDTO.getPrice())));
        productService.createProduct(product);

        return ResponseEntity.ok(product);

    }

    @PutMapping("/update/{id}")
    public void updateProduct(@RequestBody Product productDTO, @PathVariable("id") Long id) {
        Product updateProduct = productService.findProductById(id);
        updateProduct.setName(productDTO.getName());
        updateProduct.setPrice(productDTO.getPrice());
        updateProduct.setCategory(productDTO.getCategory());
        updateProduct.setDiscount(productDTO.getDiscount());
        updateProduct.setDescription(productDTO.getDescription());
        actualPriceCalculate(productDTO);
        productService.changeProductInformation(id, updateProduct);


    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping
    public List<Product> find() {
        return productService.findAll();

    }
    @GetMapping("/category/{category}")
    public List findProductsByCategory(@PathVariable("category") Category category){
        return productService.findAllCategories(category);
    }

    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        return new ProductDTO(product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getDiscount(), product.getActual_price(), product.getDescription());
    }
}
