/*
package com.serkanguner.config.security;


import com.serkanguner.entity.UserProfile;
import com.serkanguner.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {

    private final UserProfileService userProfileService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByAuthid(Long authid) {
        UserProfile userProfile = userProfileService.findByAuthId(authid);

        return User.builder()
                .username(userProfile.getUsername())
                .password("")
                .build();
    }
}
*/
