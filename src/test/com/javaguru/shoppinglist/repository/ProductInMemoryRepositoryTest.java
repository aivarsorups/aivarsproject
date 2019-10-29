package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductInMemoryRepositoryTest {
    private static final String PRODUCT_NAME = "TEST_NAME";
    private static final String PRODUCT_DESCRIPTION = "TEST_DESCRIPTION";
    private static final long PRODUCT_ID = 0L;
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal(100);
    private static final BigDecimal PRODUCT_DISCOUNT = new BigDecimal(50);
    private static final BigDecimal PRODUCT_ACTUALPRICE=new BigDecimal("50.0");
    private ProductInMemoryRepository victim = new ProductInMemoryRepository();
    private Product product = product();

    @Test
    public void shouldSave() {
        Product result = victim.save(product);
        assertThat(result).isEqualTo(product );

    }
    @Test
    public void shouldFindAllProducts(){
        victim.save(product);
        victim.save(expectedProduct());
        expectedProduct().setId(1L);



        List result=victim.findAllProducts();
        assertThat(product).isIn(result);
        assertThat(expectedProduct()).isIn(result);


    }

    @Test
    public void findProductById() {
        victim.save(product);
        Optional<Product> result = victim.findProductById(PRODUCT_ID);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProduct());
    }

    @Test
    public void deleteProductById() {
        Product result1=victim.save(product);
        assertThat(result1).isEqualTo(product);
        victim.deleteProductById(0L);
        try{Optional<Product> result = victim.findProductById(0L);
        assertThat(result).isEqualTo(Optional.empty());}catch (NullPointerException r){
            System.out.println("test completed");
        }


    }
    @Test
    public void shouldFindAllProductByCategory(){

        assert(victim.findAllCategories(Category.FRUITS).isEmpty());
        victim.save(product);
        product.setCategory(Category.FRUITS);
        List result=victim.findAllCategories(Category.FRUITS);
       assertThat(product).isIn(result);
    }


    @Test
    public void changeProductInformation() {

        victim.save(product);
        Product result = victim.changeProductInformation(0L, product);
        product.setActualPrice(PRODUCT_ACTUALPRICE);
        product.setId(PRODUCT_ID);
        assertThat(result).isEqualTo(product);
    }

    @Test
    public void existsByName() {
        victim.save(product);
        boolean result = victim.existsByName(PRODUCT_NAME);
        assertThat(result).isTrue();
    }

    private Product product() {
        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }

    private Product expectedProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setActualPrice(PRODUCT_ACTUALPRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }
    private Product expectedProductWithNullActualPrice() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }

}