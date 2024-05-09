package com.serkanguner.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    USERNAME_ALREADY_TAKEN(1001,
            "Bu username daha önce kullanılmış. Yeniden deneyiniz.",
            HttpStatus.BAD_REQUEST),
    USERNAME_OR_PASSWORD_WRONG(1002,
            "Kullanıcı adı veya parola yanlış.",
            HttpStatus.BAD_REQUEST),
    PASSWORD_AND_REPASSWORD_NOT_EQUALS(1003,"Sifreler uyusmamaktadir. Tekrar deneyiniz!",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(2001,"Token Gecersizdir",HttpStatus.BAD_REQUEST),

    TOKEN_CREATION_FAILED(2002,"Token yaratmada bir hata meydane geldi",HttpStatus.SERVICE_UNAVAILABLE),
    TOKEN_VERIFY_FAILED(2003,"Token verify etmede bir hata meydana geldi.",HttpStatus.SERVICE_UNAVAILABLE),
    TOKEN_ARGUMENT_NOTVALID(2004,"Token argumani yanlis formatta.",HttpStatus.BAD_REQUEST);

    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}