package com.serkanguner.controller;

import com.serkanguner.Service.MailSenderService;
import com.serkanguner.dto.request.InfoDto;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
@RequiredArgsConstructor
public class MailController {
    private final MailSenderService mailSenderService;



    @GetMapping("/mailat")
    public void sendMail(InfoDto info) {
        mailSenderService.sendMail(info);

    }
}
