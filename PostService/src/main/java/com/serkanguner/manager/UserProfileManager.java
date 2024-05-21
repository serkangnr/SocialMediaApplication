package com.serkanguner.manager;

import com.serkanguner.constant.EndPoints;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9092/api/v1/userprofile", name = "userprofile")
public interface UserProfileManager {
    @GetMapping("/getUserIdToken")
    String getUserIdToken(@RequestParam(name = "AuthId") Long authId);

    @PutMapping(EndPoints.ACTIVATEACCOUNT + "/{authId}")
    ResponseEntity<Boolean> activateUserProfile(@PathVariable Long authId);
}

