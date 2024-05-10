package com.aryak.product.controller;

import com.aryak.product.dto.ProductDto;
import com.aryak.product.model.Product;
import com.aryak.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

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
    public ProductDto addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
}
