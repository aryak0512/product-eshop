package com.aryak.product;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class ProductEshopApplication {


    @Generated
    public static void main(String[] args) {
        SpringApplication.run(ProductEshopApplication.class, args);
    }



}
