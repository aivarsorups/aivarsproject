package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindProductByIdAction implements Action {

    private static final String ACTION_NAME="Find product by id";

    private final ProductService productService;

    public FindProductByIdAction(ProductService productService){
        this.productService=productService;
    }
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        Long id = scanner.nextLong();
        Product product = productService.findProductById(id);
        System.out.println(product);

    }
    @Override
    public String toString(){
        return ACTION_NAME;
    }
}
