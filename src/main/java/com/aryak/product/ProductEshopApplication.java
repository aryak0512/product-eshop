package com.aryak.product;

import com.aryak.product.dto.ProductDto;
import com.aryak.product.model.Product;
import com.aryak.product.rabbitmq.RabbitService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductEshopApplication implements CommandLineRunner {

    @Autowired
    RabbitService rabbitService;

    @Generated
    public static void main(String[] args) {
        SpringApplication.run(ProductEshopApplication.class, args);
    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        Product product1 = Product.builder().description("Latest Iphone").name("Iphone 15 max").price(470.00).build();
        Product product2 = Product.builder().description("Latest Iphone").name("Iphone 15 pro").price(270.00).build();
        rabbitService.send(product1, "aryak_exchange" ,"aryak_routing_key" );
        rabbitService.send(product2, "aryak_exchange" ,"aryak_routing_key" );
    }
}
