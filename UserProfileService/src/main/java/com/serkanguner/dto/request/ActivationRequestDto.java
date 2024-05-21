package com.serkanguner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ActivationRequestDto {
    Long id;
    String activationCode;
    String authId;
}
