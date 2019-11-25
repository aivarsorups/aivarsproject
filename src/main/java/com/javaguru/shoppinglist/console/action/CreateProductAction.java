package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.util.Scanner;
@Component
public class CreateProductAction implements Action {

    private static final String ACTION_NAME="Create product";

    private final ProductService productService;

    public CreateProductAction(ProductService productService){
        this.productService=productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product category FRUITS or VEGETABLES");
        Category category = Category.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter product name: ");
        String name = scanner.nextLine();

        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        System.out.println("Enter product discount");
        BigDecimal discount = new BigDecimal(scanner.nextLine());


        System.out.println("Enter description");
        String description = scanner.nextLine();

        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setDescription(description);
        try{Long id = productService.createProduct(product);
        System.out.println("Result: " + id);}catch(ProductValidationException e){
            System.out.println(e.getMessage());
        }

    }
    @Override
    public String toString(){
        return ACTION_NAME;
    }
}
