package com.serkanguner.exception;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

//Bu sinif tum controller siniflari icin merkezi bir sekilde hata yonetimmi saglayacaktir.
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class) // RuntimeException Hata yakalayici bir metod oldugunu belirtmek icin.
    public ResponseEntity<String> handleException(){
        return ResponseEntity.badRequest().body("Uygulamada Runtime Exception Olustu...........");
    }


    @ExceptionHandler(AuthServiceException.class)
    public ResponseEntity<ErrorMessage> handleDemoException(AuthServiceException authServiceException){
        ErrorType errorType = authServiceException.getErrorType();

        return new ResponseEntity(createErrorMessage(authServiceException),errorType.getHttpStatus());
    }

    private ErrorMessage createErrorMessage(AuthServiceException authServiceException) {
       return ErrorMessage.builder()
               .code(authServiceException.getErrorType().getCode())
               .message(authServiceException.getMessage())
               .build();
    }

    private ErrorMessage createErrorMessage(Exception ex, ErrorType errorType) {
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createErrorMessage(exception,
                errorType);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage,
                errorType.getHttpStatus());
    }



}
