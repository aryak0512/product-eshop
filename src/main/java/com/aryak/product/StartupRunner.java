package com.aryak.product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Value("${eshop.datasource.username}")
    private String username;

    @Value("${eshop.datasource.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Decrypted username: " + username);
        System.out.println("Decrypted password: " + password);
    }
}

