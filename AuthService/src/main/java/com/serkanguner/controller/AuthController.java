package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.LoginRequestDto;
import com.serkanguner.dto.request.RegisterRequestDto;
import com.serkanguner.dto.response.LoginResponseDto;
import com.serkanguner.dto.response.RegisterResponseDto;
import com.serkanguner.entity.Auth;
import com.serkanguner.exception.AuthMicroServiceException;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.mapper.AuthMapper;
import com.serkanguner.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(EndPoints.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Register İşlemleri:
     */
    @PostMapping(EndPoints.REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto) {
        Auth auth = authService.save(dto);
        return ResponseEntity.ok(AuthMapper.INSTANCE.authToDto(auth)) ;
    }

    /**
     * Login İşlemleri
     */
    @PostMapping(EndPoints.LOGIN)
    public ResponseEntity<String> dologin(@RequestBody LoginRequestDto dto) {
        Auth auth = authService.findOptionalByUsernameAndPassword(dto.getUsername(),
                dto.getPassword());
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @GetMapping(EndPoints.FINDALL)
    public ResponseEntity<List<Auth>> findAll(String token){
        return ResponseEntity.ok(authService.findAll(token));

    }
}
