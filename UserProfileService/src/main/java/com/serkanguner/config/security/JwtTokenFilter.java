/*
package com.serkanguner.config.security;


import com.serkanguner.exception.ErrorType;
import com.serkanguner.exception.UserServiceException;
import com.serkanguner.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;
    private final JwtUserDetails jwtUserDetails;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //System.out.println("JwtTokenFilter filtresi devrede!!!");

        //requestte gelen bearer tokeni yakalama:
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null) {

            String token = bearerToken.substring(7);
            System.out.println("JwtTokenFilter ile yakalanan token:" + token);
            Long authId =
                    jwtTokenManager.getIdFromToken(token)
                            .orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));
            System.out.println("JwtTokenFilter ile yakalanan authId:" + authId);


            UserDetails userDetails = jwtUserDetails.loadUserByAuthid(authId);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails,
                            null,
                            userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            System.out.println(userDetails.getAuthorities());


        }

        filterChain.doFilter(request,
                response);

    }
}
*/
