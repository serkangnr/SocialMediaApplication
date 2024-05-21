package com.serkanguner.manager;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.ActivationRequestDto;
import com.serkanguner.dto.request.UserProfileUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(url = "http://localhost:9090/api/v1/auth", name = "authmanager",dismiss404 = true)
public interface AuthManager {

    @PutMapping("/email/{authId}")
    public ResponseEntity<Boolean> updateEmail(@PathVariable Long authId, @RequestBody UserProfileUpdateRequestDto dto);
}
