package com.serkanguner.config.rabbit;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //1.adim kuyruklari olusturduk.

    @Bean
    Queue queueA(){
        return new Queue("q.A");
    }
    @Bean
    Queue queueB(){
        return new Queue("q.B");
    }
    @Bean
    Queue queueC(){
        return new Queue("q.C");
    }

    @Bean
    Queue queueDelete(){
        return new Queue("q.Delete");
    }


    //2.adim Exchange olusturalim
    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");
    }


    //3.adim bingind olusturma
    @Bean
    Binding bindingA(Queue queueA, DirectExchange exchange){
        return BindingBuilder
                .bind(queueA)
                .to(exchange)
                .with("Routing.A");

    }
    @Bean
    Binding bindingMail(Queue queueB, DirectExchange exchange){
        return BindingBuilder
                .bind(queueB)
                .to(exchange)
                .with("Routing.B");

    }
    @Bean
    Binding bindingC(Queue queueC, DirectExchange exchange){
        return BindingBuilder
                .bind(queueC)
                .to(exchange)
                .with("Routing.C");

    }
    @Bean
    Binding bindingDelete(Queue queueDelete, DirectExchange exchange){
        return BindingBuilder
                .bind(queueDelete)
                .to(exchange)
                .with("Routing.Delete");

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
