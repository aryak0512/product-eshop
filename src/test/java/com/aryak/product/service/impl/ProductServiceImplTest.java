package com.aryak.product.service.impl;

import com.aryak.product.exceptions.ProductNotFoundException;
import com.aryak.product.model.Product;
import com.aryak.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService; // class under test

    @Mock
    private ProductRepository productRepository;

    @Test
    void getAllProducts() {

        // Mock the behavior of the repository
        when(productRepository.findAll()).thenReturn(
                List.of(Product.builder()
                        .price(0.45)
                        .productId(565436L)
                        .description("Brand new iphone launched")
                        .name("Iphone 15")
                        .build())
        );

        List<Product> products = productService.getAllProducts();
        assertThat(products).isNotEmpty();
    }

    @Test
    void findProductByName() {

        // given
        var name ="Iphone 15";
        // when
        when(productRepository.findByName(name)).thenReturn(
                List.of(Product.builder()
                        .price(0.45)
                        .productId(565436L)
                        .description("Brand new iphone launched")
                        .name("Iphone 15")
                        .build())
        );
        List<Product> productByName = productService.findProductByName(name);

        // then
        assertThat(productByName).hasSize(1);
    }

    @Test
    void findProductById_success() {

        // given
        var id = 565436;

        // when
        when(productRepository.findById(id)).thenReturn(
                Optional.of(Product.builder()
                        .price(0.45)
                        .productId(565436L)
                        .description("Brand new iphone launched")
                        .name("Iphone 15")
                        .build())
        );

        var product = productService.findProductById(id);

        // then
        assertThat(product.getProductId()).isEqualTo(id);
    }

    @Test
    void findProductById_failure() {

        // given
        var id = 37557635;

        // then
        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(id));
    }

}