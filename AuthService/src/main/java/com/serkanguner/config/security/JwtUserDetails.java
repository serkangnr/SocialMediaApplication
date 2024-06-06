package com.serkanguner.config.security;

import com.serkanguner.entity.Auth;
import com.serkanguner.service.AuthService;
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

    private final AuthService authService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByAuthid(Long authid) {
         Auth auth = authService.findbyId(authid);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authService.findAllById(auth.getId()).forEach(auth1->{
            authorities.add(new SimpleGrantedAuthority(auth1.getRole().name()));
            authorities.add(new SimpleGrantedAuthority(auth1.getRole().name()));
        });



        return User.builder()
                .username(auth.getUsername())
                .password("")
                .authorities(authorities)
                .build();
    }
}
