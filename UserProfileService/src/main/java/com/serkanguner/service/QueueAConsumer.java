package com.serkanguner.service;


import com.serkanguner.dto.request.UserProfileSaveRequestDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class QueueAConsumer {
    @RabbitListener(queues = "q.A")
    public void receiveMessageFromQueueA(UserProfileSaveRequestDto dto){
        System.out.println("q.A'dan gelen mesaj"+dto);
    }

}
