package com.serkanguner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserProfileSaveRequestDto {
    String username;
    String email;
    Long authId;


}
//AuthService de register olduktan sonra activasyon işlemleri yapılırken bu işlem UserProfile tarafına yansıtılmalı. (openfeign) UserProfile tarafında endpointte pathVariable ile authid alabilirsiniz. -- UserService kapalı veya bir nedenden işlem gerçekleşemezse authservicede de bu işlem geriye alınmalı.