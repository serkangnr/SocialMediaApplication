package com.serkanguner.dto.response;

import com.serkanguner.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterResponseDto {
    String username;
    Status status;
    LocalDateTime create_at;


}
