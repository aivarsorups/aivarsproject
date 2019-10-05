package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {

    private ProductService productService = new ProductService();

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create task");
                System.out.println("2. Find task by id");
                System.out.println("3. Exit");
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findProduct();
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }

    }
    private void createProduct(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter product category FRUITS or VEGETABLES");

        Category category=Category.valueOf(scanner.nextLine());
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        System.out.println("Enter product discount");
        BigDecimal discount = new BigDecimal(scanner.nextLine());
        BigDecimal actualPrice = price.subtract(discount.divide(new BigDecimal(100)).multiply(price));
        System.out.println("Enter description");
        String description = scanner.nextLine();
        Product product = new Product(category, name, price, discount, actualPrice, description);
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setActualPrice(actualPrice);
        product.setDescription(description);
        Long id=productService.createProduct(product);
        System.out.println("Result: " + id);

    }
    private void findProduct(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter product id: ");
        Long id=scanner.nextLong();
        Product product=productService.findProductById(id);
        System.out.println(product);
    }
}
