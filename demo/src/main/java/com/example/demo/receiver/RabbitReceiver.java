package com.example.demo.receiver;

import java.io.IOException;

import com.blade.ioc.annotation.Bean;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
@Bean
@EnableRabbit
public class RabbitReceiver {
    @RabbitListener(containerFactory = "myRabbitListenerContainerFactory", queues = "demo-queue")
    public void getMessage(String msg) {
        System.out.println("the message is "+msg);
        try {
            // ConnectionFactory factory = new ConnectionFactory();
            // factory.setUri("amqp://guest:guest@localhost:15672/");
            // Connection conn = factory.newConnection();
            // Channel c = conn.createChannel();
            // c.queueDeclare("demo-queue", false, true, false, null);
            // boolean autoAck = false;
            // c.basicConsume("demo-queue", autoAck, "myConsumerTag", new DefaultConsumer(c) {
            //     @Override
            //     public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
            //             byte[] body) throws IOException {
            //         String routingKey = envelope.getRoutingKey();
            //         String contentType = properties.getContentType();
            //         long deliveryTag = envelope.getDeliveryTag();
            //         // (process the message components here ...)
            //         System.out.println(body.toString());
            //         c.basicAck(deliveryTag, false);
            //     }
            // });
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
