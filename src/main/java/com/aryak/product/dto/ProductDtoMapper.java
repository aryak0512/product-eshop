package com.aryak.product.dto;

import com.aryak.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    /**
     * created using generate all setters plugin
     **/
    public Product map(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return product;
    }

    /**
     * created using generate all setters plugin
     **/
    public ProductDto mapToDto(Product savedProduct) {
        ProductDto productDto = new ProductDto();
        productDto.setName(savedProduct.getName());
        productDto.setDescription(savedProduct.getDescription());
        productDto.setPrice(savedProduct.getPrice());
        return productDto;
    }
}
