package com.aryak.product.service.impl;

import com.aryak.product.dto.ProductDto;
import com.aryak.product.dto.ProductDtoMapper;
import com.aryak.product.exceptions.ProductNotFoundException;
import com.aryak.product.model.Product;
import com.aryak.product.repository.ProductRepository;
import com.aryak.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Product service.
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper mapper;

    public ProductServiceImpl(ProductDtoMapper mapper, ProductRepository productRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findProductById(long id) {

        Optional<Product> optionalProduct = productRepository.findById((int) id);
        if ( optionalProduct.isEmpty() ) {
            throw new ProductNotFoundException(3000, "Product with ID :" + id + " does not exist!");
        }
        return optionalProduct.get();
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {

        Product product = mapper.map(productDto);
        Product savedProduct = productRepository.save(product);
        log.info("Saved product to DB : {}", product);
        return mapper.mapToDto(savedProduct) ;
    }
}
