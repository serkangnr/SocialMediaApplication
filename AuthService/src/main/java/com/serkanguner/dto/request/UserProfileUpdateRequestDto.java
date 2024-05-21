package com.serkanguner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserProfileUpdateRequestDto {
    String token;
    String id;
    String email;
    String photo;
    String phone;
    String address;
    String about;

}
