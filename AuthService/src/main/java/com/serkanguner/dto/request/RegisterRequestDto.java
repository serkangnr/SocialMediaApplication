package com.serkanguner.dto.request;

import com.serkanguner.constant.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequestDto {
    String username;
    @Email(regexp = "@gmail.com")
    String email;
    @Size(min = 3,max = 8)
    String password;
    String repassword;
    Role role;
}
