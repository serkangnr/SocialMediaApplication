package com.serkanguner.dto.request;

import com.serkanguner.constant.Role;
import com.serkanguner.utility.lowercase.Lowercase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.util.Locale;

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
