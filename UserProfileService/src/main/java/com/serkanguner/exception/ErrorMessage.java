package com.serkanguner.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Eger valid'de fields kismi bos ise null olarak gostermememsi icin
public class ErrorMessage {
    private Integer code;
    private String message;
    List<String> fields;
    @Builder.Default
    private LocalDateTime dateTime=LocalDateTime.now();
}
