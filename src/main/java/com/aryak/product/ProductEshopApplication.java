package com.aryak.product;

import com.aryak.product.rabbitmq.RabbitService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.Generated;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class ProductEshopApplication implements CommandLineRunner {

    //@Autowired
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
//        Product product1 = Product.builder().description("Latest Iphone").name("Iphone 15 max").price(470.00).build();
//        Product product2 = Product.builder().description("Latest Iphone").name("Iphone 15 pro").price(270.00).build();
//        rabbitService.send(product1, "aryak_exchange" ,"aryak_routing_key" );
//        rabbitService.send(product2, "aryak_exchange" ,"aryak_routing_key" );
    }
}
