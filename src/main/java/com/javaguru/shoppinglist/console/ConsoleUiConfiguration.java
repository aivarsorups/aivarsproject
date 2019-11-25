package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.console.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUiConfiguration {
    private final Action createProductAction;
    private final Action findProductByIdAction;
    private final Action changeProductInformationAction;
    private final Action deleteProductAction;
    private final Action findAllProductsAction;
    private final Action allProductsByCategoryAction;

    @Autowired
    public ConsoleUiConfiguration(Action createProductAction,
                                  Action findProductByIdAction,
                                  Action changeProductInformationAction,
                                  Action deleteProductAction,
                                  Action findAllProductsAction,
                                  Action allProductsByCategoryAction){
        this.createProductAction=createProductAction;
        this.findProductByIdAction=findProductByIdAction;
        this.changeProductInformationAction=changeProductInformationAction;
        this.deleteProductAction=deleteProductAction;
        this.findAllProductsAction=findAllProductsAction;
        this.allProductsByCategoryAction=allProductsByCategoryAction;
    }
    @Bean
    ConsoleUI consoleUI(){
        List<Action>actions=new ArrayList<>();
        actions.add(createProductAction);
        actions.add(findProductByIdAction);
        actions.add(changeProductInformationAction);
        actions.add(deleteProductAction);
        actions.add(findAllProductsAction);
        actions.add(allProductsByCategoryAction);
        return new ConsoleUI(actions);

    }
}
