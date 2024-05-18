package com.aryak.product.repository;

import com.aryak.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryImpl {

    @Autowired
    private JdbcTemplate postgresTemplate;

    public List<Product> fetchAll() {
        ProductRowMapper productRowMapper = new ProductRowMapper();
        return postgresTemplate.query("select * from products", productRowMapper);
    }
}
