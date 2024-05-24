package com.serkanguner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserProfileSaveRequestRedisDto implements Serializable {
    String username;
    String email;
    Long authId;


}
