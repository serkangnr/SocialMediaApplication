package com.serkanguner.dto.request;

import com.serkanguner.constant.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @Size(min = 3, max = 20,message = "Username min 3 max 20 karakter olabilir.")
    @NotBlank(message = "Username bos gecilemez")
    String username;
    @NotBlank(message = "Email bos gecilemez")
    @Email(message = "Gecerli bir eposta adresi giriniz.")
    String email;
    @NotBlank(message = "Password bos gecilemez")
    @Size(min = 3, max = 8)
    String password;
    String repassword;
}
