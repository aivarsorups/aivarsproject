package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {

    private final ProductService productService;

    public ConsoleUI(ProductService productService) {
        this.productService = productService;
    }

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Delete product by id");
                System.out.println("4. Change product information");
                System.out.println("5. Exit");
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findProduct();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        changeProductInformation();
                        break;
                    case 5:
                        return;

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product category FRUITS or VEGETABLES");
        Category category = Category.valueOf(scanner.nextLine());

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
        Long id = productService.createProduct(product);
        System.out.println("Result: " + id);

    }

    private void findProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        Long id = scanner.nextLong();
        Product product = productService.findProductById(id);
        System.out.println(product);
    }

    private void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id to delete");
        Long id = scanner.nextLong();
        productService.deleteProductById(id);
    }

    private void changeProductInformation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product id");
        Long id = Long.valueOf(scanner.nextLine());
        productService.findProductById(id);

        System.out.println("Enter product category FRUITS or VEGETABLES");
        Category category = Category.valueOf(scanner.nextLine());

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

        productService.changeProductInformation(id, product);

        System.out.println("Result: " + id);
        System.out.println(product);
    }
}
