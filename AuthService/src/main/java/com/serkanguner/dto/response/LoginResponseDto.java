package com.serkanguner.dto.response;

import com.serkanguner.constant.Role;
import com.serkanguner.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginResponseDto {
    Long id;
    String username;
    String email;
    Role role;
    Status status;

}
