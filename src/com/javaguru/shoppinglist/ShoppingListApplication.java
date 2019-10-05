package com.javaguru.shoppinglist;



import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;
import java.util.HashMap;
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
                System.out.println("3. Delete product");
                System.out.println("4. Change product information");
                System.out.println("5. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Enter product category");

                        Category category=Category.valueOf(scanner.nextLine());
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
                        BigDecimal actualPrice = price.subtract(discount.divide(new BigDecimal(100)).multiply(price));
                        System.out.println("Enter description");
                        String description = scanner.nextLine();
                        Product product = new Product(category, name, price, discount, actualPrice, description);
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
                        System.out.println(productRepository.values());
                        System.out.println("Enter product id to delete");
                        long idToDelete = scanner.nextLong();
                        if (productRepository.containsKey(idToDelete)) {
                            productRepository.remove(idToDelete);
                        } else {
                            System.out.println("wrong id");
                        }
                        break;
                    case 4:
                        System.out.println(productRepository.values());
                        System.out.println("Enter product id to change information");
                        long idToChangeProduct = scanner.nextLong();
                        if (!productRepository.containsKey(idToChangeProduct)){break;}else {
                            System.out.println("Enter product category");

                            Category newCategory=Category.valueOf(scanner.nextLine());
                            System.out.println("Enter product name: ");
                            String newName = scanner.next();
                            if ((newName.length() < 3) || (newName.length() > 32)) {
                                System.out.println("Name is to short");
                                break;
                            }
                            System.out.println("Enter product price: ");
                            BigDecimal newPrice = new BigDecimal(scanner.next());
                            if (newPrice.compareTo(BigDecimal.ZERO) <= 0) {
                                System.out.println("Price must be positive number");
                                break;
                            }
                            System.out.println("Enter product discount");
                            BigDecimal newDiscount = new BigDecimal(scanner.next());
                            if ((newDiscount.compareTo(BigDecimal.valueOf(100))) > 0) {
                                System.out.println("Discount cant be more than 100%");
                                break;
                            }
                            BigDecimal newActualPrice = newPrice.subtract(newDiscount.divide(new BigDecimal(100)).multiply(newPrice));
                            System.out.println("Enter description");
                            String newDescription = scanner.next();
                            Product newProduct = new Product(newCategory, newName, newPrice, newDiscount, newActualPrice, newDescription);
                            newProduct.setCategory(newCategory);
                            newProduct.setName(newName);
                            newProduct.setPrice(newPrice);
                            newProduct.setDiscount(newDiscount);
                            newProduct.setActualPrice(newActualPrice);
                            newProduct.setDescription(newDescription);
                            newProduct.setId(idToChangeProduct);
                            productRepository.put(idToChangeProduct, newProduct);
                            System.out.println("Id to chage"+idToChangeProduct);
                            break;
                        }

                    case 5:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }
}
