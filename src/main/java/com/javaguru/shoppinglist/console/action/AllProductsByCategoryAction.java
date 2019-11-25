package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AllProductsByCategoryAction implements Action {

    private final static String ACTION_NAME = "All products by category";

    private final ProductService productService;

    public AllProductsByCategoryAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product category");
        Category category = Category.valueOf(scanner.nextLine().toUpperCase());
        System.out.println(productService.findAllCategories(category));

    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
