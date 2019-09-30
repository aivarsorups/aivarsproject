package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

class ShoppingListApplication {

    public static void main(String[] args) {
        Map<Long, Product> productRepository = new HashMap<>();
        Long productIdSequence = 0L;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Enter product category");


                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        if ((name.length() < 3) || (name.length() > 32)) {
                            System.out.println("Name is to short");
                            break;
                        }
                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        if (price.compareTo(BigDecimal.ZERO) <= 0) {
                            System.out.println("Price must be positive number");
                            break;
                        }
                        System.out.println("Enter product discount");
                        BigDecimal discount = new BigDecimal(scanner.nextLine());
                        if ((discount.compareTo(BigDecimal.valueOf(100))) > 0) {
                            System.out.println("Discount cant be more than 100%");
                            break;
                        }
                        BigDecimal actualPrice= price.subtract(discount.divide(new BigDecimal(100)).multiply(price));
                        System.out.println("Enter description");
                        String description=scanner.nextLine();
                        Product product = new Product(name, price,discount,actualPrice, description);
                        product.setName(name);
                        product.setPrice(price);
                        product.setDiscount(discount);
                        product.setActualPrice(actualPrice);
                        product.setDescription(description);
                        product.setId(productIdSequence);
                        productRepository.put(productIdSequence, product);
                        productIdSequence++;
                        System.out.println("Result: " + product.getId());
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        long id = scanner.nextLong();
                        Product findProductResult = productRepository.get(id);
                        System.out.println(findProductResult);
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }
}
