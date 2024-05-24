package com.serkanguner.gatewayservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/auth")
    public ResponseEntity<String> getFallbackAuth(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server hatasi");
    }
    @GetMapping("/auth")
    public ResponseEntity<String> getFallbackUser(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User hatasi");
    }
    @GetMapping("/auth")
    public ResponseEntity<String> getFallbackPost(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post hatasi");
    }
}
