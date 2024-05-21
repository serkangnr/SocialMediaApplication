package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.*;
import com.serkanguner.entity.UserProfile;
import com.serkanguner.mapper.UserProfileMapper;
import com.serkanguner.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(EndPoints.USERPROFILE)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;


    @PostMapping(EndPoints.SAVE)
    public ResponseEntity<Void> save (@RequestBody UserProfileSaveRequestDto dto){
        userProfileService.save(dto);
       return ResponseEntity.ok().build();
    }

    @PutMapping(EndPoints.UPDATE)
    public ResponseEntity<Void> update (@RequestBody UserProfileUpdateRequestDto dto){
        userProfileService.update(dto);
        return ResponseEntity.ok().build();
    }
    @PutMapping(EndPoints.ACTIVATEACCOUNT + "/{authId}")
    ResponseEntity<Boolean> activateUserProfile(@PathVariable Long authId){
        return ResponseEntity.ok(userProfileService.activateUserProfile(authId));
    }
    @PutMapping("/updateWithToken")
    public ResponseEntity<Boolean> updateWithToken(@RequestBody UserProfileUpdateRequestDto dto) {
        userProfileService.updateUserProfile(dto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete/{authId}")
    public ResponseEntity<Boolean> deleteUserProfile(@PathVariable Long authId) {
       userProfileService.delete(authId);
       return ResponseEntity.ok(true);
    }
    @GetMapping("/getUserIdToken")
    String getUserIdToken(@RequestParam(name = "AuthId") Long authId){
        return userProfileService.getUserIdToken(authId);
    }



}
