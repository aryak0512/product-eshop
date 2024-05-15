package com.aryak.product.rabbitmq;

import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitConsumer implements Consumer {

    RabbitTemplate rabbitTemplate;
    Channel channel;

    public RabbitConsumer(RabbitTemplate rabbitTemplate) throws IOException {

        channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(true);
        System.out.println("Inside constructor...");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(
                    String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException {

                String message = new String(body, "UTF-8");
                System.out.println("Consumed: " + message);
            }
        };
        channel.basicConsume("aryak_queue", true, consumer);

    }

    //@RabbitListener(queues = {"aryak_queue"})
    public void consume(String data) {
        System.out.println(data);
    }

    /**
     * @param s
     */
    @Override
    public void handleConsumeOk(String s) {

    }

    /**
     * @param s
     */
    @Override
    public void handleCancelOk(String s) {

    }

    /**
     * @param s
     * @throws IOException
     */
    @Override
    public void handleCancel(String s) throws IOException {

    }

    /**
     * @param s
     * @param e
     */
    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    /**
     * @param s
     */
    @Override
    public void handleRecoverOk(String s) {

    }

    /**
     * @param s
     * @param envelope
     * @param basicProperties
     * @param bytes
     * @throws IOException
     */
    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {

    }
}
