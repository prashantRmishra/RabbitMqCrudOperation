package com.example.demo.sender;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.config.Configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
@Bean
public class Rabbitsender {
    RabbitTemplate rabbitTemplate = new RabbitTemplate();
    @Inject
    Configuration config;
    public void send(String e, String Type){
        rabbitTemplate.setConnectionFactory(config.getCFactory());
        rabbitTemplate.setExchange(config.getExchange());
        rabbitTemplate.setRoutingKey(config.getRouStringKey());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.convertAndSend(e);
        System.out.println("sent :"+e);
    }
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Binding binding(FanoutExchange directExchange, Queue queue) {
    //     return BindingBuilder.bind(queue).to(directExchange);
    // }

}