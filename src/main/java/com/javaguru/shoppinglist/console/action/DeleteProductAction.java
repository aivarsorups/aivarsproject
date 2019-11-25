package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class DeleteProductAction implements Action {
    private static final String ACTION_NAME="Delete product by id";
    private final ProductService productService;
    public DeleteProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id to delete");
        Long id = scanner.nextLong();
        productService.findProductById(id);
        productService.deleteProductById(id);
    }
    @Override
    public String toString(){
        return ACTION_NAME;
    }
}
