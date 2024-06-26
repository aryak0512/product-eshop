package com.aryak.product.controller;

import com.aryak.product.dto.ProductDto;
import com.aryak.product.model.Product;

import com.aryak.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    //private final ProductRepositoryImpl impl;

    public ProductController(ProductService productService) {
        this.productService = productService;
        //this.impl = impl;
    }

    @GetMapping(value ="/")
    public ResponseEntity<List<Product>> getAllProducts(){
        log.info("Inside getAllProducts");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value ="/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name){
        log.info("Getting product by name : {}", name);
        return new ResponseEntity<>(productService.findProductByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        log.info("Getting product by ID : {}", id);
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @PostMapping(value ="/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws InterruptedException {

        var delay = new Random().nextInt(1000);
        Thread.sleep(Duration.ofMillis(delay));

        log.info("Add product endpoint called with DTO : {}", productDto);
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }

    @PostMapping(value ="/addBulk")
    public ResponseEntity<List<ProductDto>> addProducts(@RequestBody List<ProductDto> productDtos){
        log.info("Add products endpoint called with DTO : {}", productDtos);
        return new ResponseEntity<>(productService.addProducts(productDtos), HttpStatus.CREATED);
    }

    @GetMapping(value ="/v2")
    public ResponseEntity<List<Product>> getAllProductsV2(){
        log.info("Inside getAllProducts v2");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
