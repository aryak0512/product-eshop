package com.aryak.product.service;

import com.aryak.product.dto.ProductDto;
import com.aryak.product.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> findProductByName(String name);

    Product findProductById(long id);

    ProductDto addProduct(ProductDto productDto);

    List<ProductDto> addProducts(List<ProductDto> productDtos);
}
