package com.serkanguner.manager;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "http://localhost:9092/api/v1/userprofile", name = "userprofilemanager")
public interface UserProfileManager {
    @PostMapping(EndPoints.SAVE)
    ResponseEntity<Void> save(@RequestBody UserProfileSaveRequestDto dto);

    @PutMapping(EndPoints.ACTIVATEACCOUNT + "/{authId}")
    ResponseEntity<Boolean> activateUserProfile(@PathVariable Long authId);

    @DeleteMapping("/delete/{authId}")
    public ResponseEntity<Boolean> deleteUserProfile(@PathVariable Long authId);

}
