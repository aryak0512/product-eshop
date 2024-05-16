package com.aryak.product.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

//@Service
public non-sealed class RabbitServiceImpl extends RabbitService {

    private RabbitTemplate rabbitTemplate;

    public RabbitServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * @param data
     * @param exchange
     * @param key
     * @return
     */
    @Override
    public Object send(Object data, String exchange, String key) {
        return rabbitTemplate.convertSendAndReceive(exchange, key, data);
    }
}
