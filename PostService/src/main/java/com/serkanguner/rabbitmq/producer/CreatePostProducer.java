package com.serkanguner.rabbitmq.producer;

import com.serkanguner.rabbitmq.model.SavePostModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostProducer {
    private final RabbitTemplate rabbitTemplate;

    public void convertAndSend(SavePostModel model){
        rabbitTemplate.convertAndSend("direct-exchange",
                "save-post-key"
                ,model);

    }
}
