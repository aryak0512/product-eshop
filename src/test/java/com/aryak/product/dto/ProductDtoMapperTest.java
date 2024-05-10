package com.aryak.product.dto;

import com.aryak.product.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductDtoMapperTest {

    ProductDtoMapper mapper = new ProductDtoMapper();

    @Test
    @DisplayName(value = "Test mapper from entity to DTO")
    void map() {

        ProductDto dto = ProductDto.builder()
                .description("Latest Iphone")
                .name("Iphone 15 pro")
                .price(267.00)
                .build();
        Product product = mapper.map(dto);
        assertThat(product.getName()).isEqualTo(dto.getName());
        assertThat(product.getDescription()).isEqualTo(dto.getDescription());
        assertThat(product.getPrice()).isEqualTo(dto.getPrice());
    }

    @Test
    @DisplayName(value = "Test mapper from DTO to entity")
    void testMapToDto() {

        // Arrange
        Product savedProduct = new Product();
        savedProduct.setName("Iphone 15 pro");
        savedProduct.setDescription("Latest Iphone");
        savedProduct.setPrice(267.00);

        // Act
        ProductDto productDto = mapper.mapToDto(savedProduct);

        // Assert
        assertThat(productDto.getName()).isEqualTo(savedProduct.getName());
        assertThat(productDto.getDescription()).isEqualTo(savedProduct.getDescription());
        assertThat(productDto.getPrice()).isEqualTo(savedProduct.getPrice());
    }
}