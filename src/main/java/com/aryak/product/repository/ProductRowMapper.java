package com.aryak.product.repository;

import com.aryak.product.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    /**
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Product.builder()
                .description(rs.getString("description"))
                .productId(rs.getLong("product_id"))
                .price(rs.getDouble("price"))
                .name(rs.getString("name"))
                .build();
    }
}
