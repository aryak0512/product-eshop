package com.aryak.product.rabbitmq;

public sealed abstract class RabbitService permits RabbitServiceImpl {

    public abstract Object send(Object data, String exchange, String key);
}
