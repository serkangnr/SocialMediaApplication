package com.serkanguner.exception;

import lombok.Getter;


@Getter
public class ElasticServiceException extends RuntimeException {
    private ErrorType errorType;

    public ElasticServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ElasticServiceException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }


}
