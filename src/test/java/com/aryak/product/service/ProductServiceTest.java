package com.aryak.product.service;

import com.aryak.product.model.Product;
import com.aryak.product.repository.ProductRepository;
import com.aryak.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @Test
    void testGetAllProducts() {
        ProductService productService = new ProductServiceImpl(repository);
        assertThat(productService.getAllProducts()).isNotEmpty();
    }
}