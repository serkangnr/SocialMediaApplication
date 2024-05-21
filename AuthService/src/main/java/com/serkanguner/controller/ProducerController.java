package com.serkanguner.controller;


import com.serkanguner.dto.request.UserProfileSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;
    @PostMapping("/sendMessageToQueueA")
    public String convertAndSend(@RequestBody UserProfileSaveRequestDto userProfileSaveRequestDto){
        rabbitTemplate.convertAndSend("exchange.direct","Routing.A",userProfileSaveRequestDto);

        return "exchange.direct exchange'ine Routin.A routeKey kullanilarak mesaj gonderildi.";
    }
}
