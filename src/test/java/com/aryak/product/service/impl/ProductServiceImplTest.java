package com.aryak.product.service.impl;

import com.aryak.product.dto.ProductDto;
import com.aryak.product.dto.ProductDtoMapper;
import com.aryak.product.exceptions.ProductNotFoundException;
import com.aryak.product.model.Product;
import com.aryak.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService; // class under test

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDtoMapper mapper;

    @Test
    @DisplayName(value = "GET all products - Unit Test")
    void getAllProducts() {

        // given
        var brandNewIphoneLaunched = List.of(Product.builder().price(0.45).productId(565436L).description("Brand new iphone launched").name("Iphone 15").build());

        // when
        when(productRepository.findAll()).thenReturn(brandNewIphoneLaunched);
        List<Product> products = productService.getAllProducts();

        // then
        assertThat(products).isNotEmpty();
    }

    @Test
    @DisplayName(value = "GET product by name - Unit Test")
    void findProductByName() {

        // given
        var name ="Iphone 15";
        var brandNewIphoneLaunched = List.of(Product.builder().price(0.45).productId(565436L).description("Brand new iphone launched").name(name).build());

        // when
        when(productRepository.findByName(name)).thenReturn(brandNewIphoneLaunched);
        List<Product> productByName = productService.findProductByName(name);

        // then
        assertThat(productByName).hasSize(1);
    }

    @Test
    @DisplayName(value = "GET product by ID - success")
    void findProductById_success() {

        // given
        long id = 565436;
        var brandNewIphoneLaunched = Product.builder().price(0.45).productId(565436L).description("Brand new iphone launched").name("Iphone 15").build();

        // when
        when(productRepository.findByProductId(id)).thenReturn(Optional.of(brandNewIphoneLaunched));
        var product = productService.findProductById(id);

        // then
        assertThat(product.getProductId()).isEqualTo(id);
    }

    @Test
    @DisplayName(value = "GET product by ID - failure")
    void findProductById_failure() {

        // given
        var id = 37557635;

        // then
        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(id));
    }

    @Test
    @DisplayName(value = "ADD Bulk products")
    void addProducts() {

        ProductDto p1 = ProductDto.builder().description("Latest Iphone").name("Iphone 15 pro").price(270.00).build();
        ProductDto p2 = ProductDto.builder().description("Latest Iphone").name("Iphone 15 pro").price(267.00).build();

        List<ProductDto> inputDtos = List.of(p1, p2);
        List<Product> mappedProducts = inputDtos.stream()
                .map(dto -> new Product(/* map DTO to Product */))
                .collect(Collectors.toList());

        when(mapper.map(any(ProductDto.class))).thenAnswer(invocation -> {
            ProductDto dto = invocation.getArgument(0);
            // Map the DTO to Product, assuming mapper.map method maps DTO to Product
            return new Product(/* map DTO to Product */);
        });
        when(productRepository.saveAll(anyList())).thenReturn(mappedProducts);
        when(mapper.mapToDto(any(Product.class))).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            // Map the Product to DTO, assuming mapper.mapToDto method maps Product to DTO
            return new ProductDto(/* map Product to DTO */);
        });

        List<ProductDto> result = productService.addProducts(inputDtos);
        assertNotNull(result);
        assertEquals(inputDtos.size(), result.size());

    }


    @Test
    @DisplayName(value = "ADD product")
    void addProduct() {

        // given
        ProductDto productDto = ProductDto.builder().description("Latest Iphone").name("Iphone 15 pro").price(270.00).build();
        Product product = Product.builder().description("Latest Iphone").name("Iphone 15 pro").price(270.00).build();

        // when
        when(mapper.map(productDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(mapper.mapToDto(product)).thenReturn(productDto);
        var dto = productService.addProduct(productDto);

        /** behaviour verification **/
        verify(mapper, times(1)).mapToDto(product);
        verify(mapper).map(productDto);
        verify(productRepository).save(product);
        assertThat(dto.getPrice()).isEqualTo(270.00);

    }
}