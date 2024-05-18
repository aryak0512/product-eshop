package com.aryak.product.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
@ConfigurationProperties(prefix = "eshop.datasource")
public class PostgresConfiguration {

    private String url;
    private String username;
    private String password;

    // Getters and Setters for url, username, and password

    @Bean(value = "postgresTemplate")
    public JdbcTemplate myTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setFetchSize(1000);
        template.setDataSource(getDatasource());
        template.setQueryTimeout(1000);
        return template;
    }

    @Bean
    public DataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setMinIdle(2);
        return dataSource;
    }

    // Getters and Setters
    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
