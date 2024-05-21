package com.serkanguner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SocialMediaAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialMediaAuthApplication.class);
    }
}