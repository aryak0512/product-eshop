package com.aryak.product.configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

//@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbit.host:localhost}")
    private String host;

    @Value("${rabbit.port:5672}")
    private int port;

    @Value("${rabbit.username:guest}")
    private String username;

    @Value("${rabbit.password:guest}")
    private String password;

    //@Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding(String.valueOf(StandardCharsets.UTF_8));
        return rabbitTemplate;
    }

    //@Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory bean = new CachingConnectionFactory();
        bean.setHost(host);
        bean.setPort(port);
        bean.setUsername(username);
        bean.setPassword(password);
        return bean;
    }

}
