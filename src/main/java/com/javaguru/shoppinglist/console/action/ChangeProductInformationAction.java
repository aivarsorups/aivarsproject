package com.javaguru.shoppinglist.console.action;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ChangeProductInformationAction implements Action {
    private final static String ACTION_NAME = "Change product information";
    private final ProductService productService;

    public ChangeProductInformationAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product id");
        Long id = Long.valueOf(scanner.nextLine());
        Product product2=productService.findProductById(id);



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
        product.setId(id);
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setDescription(description);
        productService.changeProductInformation(id, product);

        System.out.println("Result: " + id);
        System.out.println("product="+product);


    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
