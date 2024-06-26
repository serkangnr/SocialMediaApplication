package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.constant.Status;
import com.serkanguner.dto.request.*;
import com.serkanguner.entity.UserProfile;
import com.serkanguner.mapper.UserProfileMapper;
import com.serkanguner.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/findbyusername")
    public ResponseEntity<List<UserProfile>> findByUsername(@RequestParam(name = "username") String username){
        return ResponseEntity.ok(userProfileService.getUserProfileByUsername(username));
    }
    @GetMapping("/findbystatus")
    public ResponseEntity<List<UserProfile>> findByStatus(@RequestParam(name = "status") Status status){
        return ResponseEntity.ok(userProfileService.getUserProfileByStatus(status));
    }
    @GetMapping(EndPoints.FINDALL)
    public ResponseEntity<List<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }



}
