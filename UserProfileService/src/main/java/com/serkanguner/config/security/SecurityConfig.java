package com.serkanguner.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //private final JwtTokenFilter jwtTokenFilter;
    //private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("SecurityFilterChain çalıştı galiba...");

        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                        .requestMatchers("userprofile/findall/**").permitAll()
                        .requestMatchers("api/v1/userprofile/findall/**").permitAll()

                        .anyRequest().authenticated())
//                .addFilterBefore(jwtTokenFilter,
//                        UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());
				/*.cors(httpSecurityCorsConfigurer ->
						      httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource));
*/		return httpSecurity.build();

    }
}
