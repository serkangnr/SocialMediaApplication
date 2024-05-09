package com.serkanguner.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ErrorMessage {
    private Integer code;
    private String message;
    List<String> fields;
}
