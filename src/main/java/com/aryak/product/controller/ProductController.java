package com.aryak.product.controller;

import com.aryak.product.dto.ProductDto;
import com.aryak.product.model.Product;
import com.aryak.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value ="/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping(value ="/{name}")
    public List<Product> getProductByName(@PathVariable String name){
        return productService.findProductByName(name);
    }

    @GetMapping(value ="/id/{id}")
    public Product getProductById(@PathVariable long id){
        return productService.findProductById(id);
    }

    @PostMapping(value ="/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        log.info("Add product endpoint called with DTO : {}", productDto);
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }
}
