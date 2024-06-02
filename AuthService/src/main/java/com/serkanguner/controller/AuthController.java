package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.constant.Role;
import com.serkanguner.dto.request.*;
import com.serkanguner.dto.response.ActivationResponseDto;
import com.serkanguner.dto.response.LoginResponseDto;
import com.serkanguner.dto.response.RegisterResponseDto;
import com.serkanguner.entity.Auth;
import com.serkanguner.mapper.AuthMapper;
import com.serkanguner.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPoints.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RabbitTemplate rabbitTemplate;

    /**
     * Register İşlemleri:
     */
    @PostMapping(EndPoints.REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        Auth auth = authService.save(dto);
        return ResponseEntity.ok(AuthMapper.INSTANCE.authToDto(auth));
    }

    /**
     * Login İşlemleri
     */
    @PostMapping(EndPoints.LOGIN)
    public ResponseEntity<String> dologin(@RequestBody LoginRequestDto dto) {
        Auth auth = authService.findOptionalByEmailAndPassword(dto.getEmail(),
                dto.getPassword());
        return ResponseEntity.ok(authService.doLogin(dto));
    }
    @PostMapping("/login2")
    public ResponseEntity<String> dologin2(@RequestBody LoginRequestDto dto) {
        Auth auth = authService.findOptionalByEmailAndPassword(dto.getEmail(),
                dto.getPassword());
        return ResponseEntity.ok(authService.doLoginWithRole(dto));
    }

    @GetMapping(EndPoints.FINDALL)
    public ResponseEntity<List<Auth>> findAll(String token) {
        return ResponseEntity.ok(authService.findAll(token));

    }
    @GetMapping("/findall2")
    public ResponseEntity<List<Auth>> findAll2(String token) {
        return ResponseEntity.ok(authService.findAll2(token));

    }

    @PutMapping(EndPoints.ACTIVATEACCOUNT)
    public ResponseEntity<String> activationAccount(@RequestBody ActivationRequestDto dto) {
        ResponseEntity.ok( authService.activationAccount(dto));
        return ResponseEntity.ok("Hesabınız Aktif Edilmiştir");
    }

    @DeleteMapping(EndPoints.SOFTDELETE+"/{id}")
    public ResponseEntity<String> softDeleteAccount(@PathVariable("id") Long authId) {
        ResponseEntity.ok(authService.softDelete(authId));
        return ResponseEntity.ok("Hesap Silinmiştir");
    }

    @PutMapping("/email/{authId}")
    public ResponseEntity<Boolean> updateEmail(@PathVariable Long authId, @RequestBody UserProfileUpdateRequestDto dto) {
        return ResponseEntity.ok(authService.updateEmail(authId, dto));
    }


    @GetMapping("/getToken")
    public ResponseEntity<String> getToken(Long id) {
        return ResponseEntity.ok(authService.getToken(id));
    }
    @GetMapping("/getRoleToken")
    public ResponseEntity<String> getRoleToken(Long id, Role role) {
        return ResponseEntity.ok(authService.getRoleToken(id, role));
    }
    @GetMapping("getIdFromToken")
    public ResponseEntity<Long> getIdFromToken(String token) {
        return ResponseEntity.ok(authService.getIdFromToken(token));
    }
    @GetMapping("getRoleFromToken")
    public ResponseEntity<Role> getRoleFromToken(String token) {
        return ResponseEntity.ok(authService.getRoleFromToken(token));
    }
    @PutMapping("/forgetpassword/{authId}")
    public ResponseEntity<String> forgetPassword(@PathVariable Long authId,@RequestBody UpdatePasswordDto dto) {
        ResponseEntity<Boolean> responseEntity = ResponseEntity.ok(authService.updatePassword(authId, dto));
        if (!responseEntity.equals(false)) {
            return ResponseEntity.ok("Şifreniz Guncellenmistir");
        }else {
            return ResponseEntity.ok("Şifreniz Guncellenememistir");
        }

    }
    @GetMapping("/sifremiunuttum")
    public ResponseEntity<String> sifremiUnuttum(String email) {
        ResponseEntity.ok(authService.sifremiUnuttum(email));
        return ResponseEntity.ok("Sifrenizi onaylamak icin forgetpassword bolumune gidiniz.") ;
    }
}
