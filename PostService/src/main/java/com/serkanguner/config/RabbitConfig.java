package com.serkanguner.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //1.adim kuyruklari olusturduk.

    @Bean
    Queue queueD(){
        return new Queue("q.D");
    }
    @Bean
    Queue queueF(){
        return new Queue("q.F");
    }
    @Bean
    Queue queueZ(){
        return new Queue("q.Z");
    }
    @Bean
    Queue queueY(){
        return new Queue("q.Y");
    }



    //2.adim Exchange olusturalim
    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");
    }




    //3.adim bingind olusturma
    @Bean
    Binding bindingD(Queue queueD, DirectExchange exchange){
        return BindingBuilder
                .bind(queueD)
                .to(exchange)
                .with("Routing.D");
    //save
    }
    @Bean
    Binding bindingF(Queue queueF, DirectExchange exchange){
        return BindingBuilder
                .bind(queueF)
                .to(exchange)
                .with("Routing.F");
    //saveAll
    }
    @Bean
    Binding bindingZ(Queue queueZ, DirectExchange exchange){
        return BindingBuilder
                .bind(queueZ)
                .to(exchange)
                .with("Routing.Z");
    }
    @Bean
    Binding bindingB(Queue queueY, DirectExchange exchange){
        return BindingBuilder
                .bind(queueY)
                .to(exchange)
                .with("Routing.Y");
        //update
    }


    //4.Adim Nesne-JSON donusumu icin Converter Ekleme
    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    //5.adim Converter'i RaabitTemplate'e atama
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}

