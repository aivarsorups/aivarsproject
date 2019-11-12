package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile({"local", "dev"})
public class DefaultProductRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    DefaultProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        String query = "insert into products(category, name, price, discount, actualPrice, description) values (" + "?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //ps.setCategory(1, product.getCategory());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setBigDecimal(4,product.getDiscount());
            ps.setBigDecimal(5, product.getActualPrice());
            ps.setString(6, product.getDescription());

            return ps;
        }, keyHolder);
        product.setId(keyHolder.getKey().longValue());

        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        String query = "select * from products were id=? ";
        List<Product> products = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(Product.class), id);
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    @Override
    public boolean existByName(String name) {
        String query="SELECT CASE WHEN count(*)>0"+"THEN true Else false END"+
                "FROM products t where t.name=?";
        return jdbcTemplate.queryForObject(query, Boolean.class,name);

    }
}
