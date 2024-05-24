package com.serkanguner.Service;

import com.serkanguner.dto.request.InfoDto;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender javaMailSender;



    @RabbitListener(queues = "q.B")
    public void sendMail(InfoDto info) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("serkan.guner@yahoo.com");
        simpleMailMessage.setTo(info.getEmail());
        simpleMailMessage.setSubject("Social Media Activation");
        simpleMailMessage.setText("Aramıza hoşgeldiniz. Uygulamada hesabınızı aktifleştirmek için lütfen "+ info.getActivationCode()+"  kodu giriniz.");

        javaMailSender.send(simpleMailMessage);
    }
    @RabbitListener(queues = "q.C")
    public void sendPasswordMail(InfoDto info) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("serkan.guner@yahoo.com");
        simpleMailMessage.setTo(info.getEmail());
        simpleMailMessage.setSubject("Social Media Forget Password");
        simpleMailMessage.setText("Sifrenizi yenilemek için lütfen "+ info.getActivationCode()+"  kodu giriniz.");

        javaMailSender.send(simpleMailMessage);
    }




}
