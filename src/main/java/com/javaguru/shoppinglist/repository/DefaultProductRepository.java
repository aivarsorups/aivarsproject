package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static com.javaguru.shoppinglist.repository.ProductInMemoryRepository.MAX_PERCENT_FOR_DISCOUNT;

@Repository
@Profile("jdbc")
public class DefaultProductRepository implements ProductRepository {
    private void calculateActualPrice(Product product) {
        BigDecimal actualPrice = product.getPrice().subtract(product.getDiscount()
                .divide(MAX_PERCENT_FOR_DISCOUNT).multiply(product.getPrice()));
        product.setActual_price(actualPrice);
    }
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    DefaultProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        String query = " INSERT INTO products2( name,category, price, discount, actualPrice, description) VALUES ( " +
                " ?,?,?,?,?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            calculateActualPrice(product);

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory().name());
            ps.setBigDecimal(3, product.getPrice());
            ps.setBigDecimal(4, product.getDiscount());
            ps.setBigDecimal(5, product.getActual_price());
            ps.setString(6, product.getDescription());

            return ps;
        }, keyHolder);
        product.setId(keyHolder.getKey().longValue());

        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        String query = " SELECT * FROM products2 WHERE  id=  " + id;
        List<Product> products = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(Product.class));
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsByName(String name) {
        String query = " SELECT CASE WHEN count(*)>0 " +
                " THEN true Else false END " +
                " FROM products2 t where t.name=? ";
        return jdbcTemplate.queryForObject(query, Boolean.class, name);

    }

    @Override
    public void deleteProductById(Long id) {
        String query = " DELETE FROM products2 WHERE  id= ? ";
        Object[] args = new Object[]{id};
        jdbcTemplate.update(query, args);

    }

    @Override
    public List findAllProductsByCategory(Category category) {
        String sql = "SELECT*FROM products2 WHERE category=?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Product.class), category.name());


    }

    @Override
    public List<Product> findAllProducts() {
        String query = "SELECT * FROM products2";
        List<Product> products2 = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(Product.class));
        return products2;
    }

    @Override
    public void changeProductInformation(Long id, Product product) {
        calculateActualPrice(product);
        String SQL = "UPDATE products2 SET  name=?, category=?, price=?, discount=?, actualPrice=?,  description=?  WHERE id=?";
        jdbcTemplate.update(SQL, product.getName(), product.getCategory().name(), product.getPrice(), product.getDiscount(), product.getActual_price(), product.getDescription(), id);

    }
}
