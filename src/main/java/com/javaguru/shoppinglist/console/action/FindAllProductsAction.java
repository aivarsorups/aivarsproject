package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class FindAllProductsAction implements Action {
    private final static String ACTION_NAME="Find all products";
    private final ProductService productService;
   public FindAllProductsAction(ProductService productService){
        this.productService=productService;
    }
    @Override
    public void execute() {
        System.out.println(productService.findAll());

    }
    @Override
    public String toString(){
       return ACTION_NAME;
    }
}
