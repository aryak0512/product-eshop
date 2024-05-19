package com.aryak.product;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShutdownRunner {

    @PreDestroy
    public void shutDown(){
        log.info("Shutdown invoked!!");
    }
}
