package com.serkanguner.exception;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Bu sinif tum controller siniflari icin merkezi bir sekilde hata yonetimmi saglayacaktir.
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class) // RuntimeException Hata yakalayici bir metod oldugunu belirtmek icin.
    public ResponseEntity<String> handleException(){
        return ResponseEntity.badRequest().body("Uygulamada Runtime Exception Olustu...........");
    }


    @ExceptionHandler(AuthMicroServiceException.class)
    public ResponseEntity<ErrorMessage> handleDemoException(AuthMicroServiceException authMicroServiceException){
        ErrorType errorType = authMicroServiceException.getErrorType();

        return new ResponseEntity(createErrorMessage(authMicroServiceException),errorType.getHttpStatus());
    }

    private ErrorMessage createErrorMessage(AuthMicroServiceException authMicroServiceException) {
       return ErrorMessage.builder()
               .code(authMicroServiceException.getErrorType().getCode())
               .message(authMicroServiceException.getMessage())
               .build();
    }



}
