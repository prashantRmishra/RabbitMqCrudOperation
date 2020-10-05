package com.example.demo.sender;

import javax.management.Notification;

import com.blade.ioc.annotation.Bean;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Bean
public class Sender {
    RabbitTemplate rabbitTemplate;
    public void sendMessage(Notification data){
        System.out.println("send message "+data);
        rabbitTemplate.convertAndSend("demo-exchange", "demo-key",data);
    }
}