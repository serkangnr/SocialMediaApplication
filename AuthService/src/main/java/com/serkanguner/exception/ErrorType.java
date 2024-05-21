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
    EMAIL_OR_PASSWORD_WRONG(1002,
            "Email veya parola yanlış.",
            HttpStatus.BAD_REQUEST),
    PASSWORD_AND_REPASSWORD_NOT_EQUALS(1003,"Sifreler uyusmamaktadir. Tekrar deneyiniz!",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_TAKEN(1004,"Email zaten kayitli" ,HttpStatus.BAD_REQUEST ),
    INVALID_TOKEN(2001,"Token Gecersizdir",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(1001,"Girilen parametreler hatalıdır. Lütfen düzeltiniz.",HttpStatus.BAD_REQUEST),
    TOKEN_CREATION_FAILED(2002,"Token yaratmada bir hata meydane geldi",HttpStatus.SERVICE_UNAVAILABLE),
    TOKEN_VERIFY_FAILED(2003,"Token verify etmede bir hata meydana geldi.",HttpStatus.SERVICE_UNAVAILABLE),
    TOKEN_ARGUMENT_NOTVALID(2004,"Token argumani yanlis formatta.",HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_ACTIVE(2005,"Hesabiniz aktif degil.",HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(1005,"Account bulunamadi" ,HttpStatus.BAD_REQUEST ),
    INVALID_ACTIVATION_CODE(1006,"Gecersiz aktivasyon kodu" ,HttpStatus.BAD_REQUEST ),
    ACCOUNT_ALREADY_ACTIVE(1007,"Hesap zaten aktif edilmis" ,HttpStatus.BAD_REQUEST ),
    ACCOUNT_ALREADY_DELETED(1008,"Hesap silinmis aktif olamaz" ,HttpStatus.BAD_REQUEST ),
    ACCOUNT_ALREADY_BANNED(1009,"Hesabiniz banlanmis aktif olamaz" ,HttpStatus.BAD_REQUEST ),
    ACCOUNT_ALREADY_PENDING(1010,"Hesabiniz pasif konumda" ,HttpStatus.BAD_REQUEST );




    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}
