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
    private final JwtTokenFilter jwtTokenFilter;
    //private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("SecurityFilterChain çalıştı galiba...");

        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                        .requestMatchers("auth/login/**","auth/register/**").permitAll()
                        .requestMatchers("api/v1/auth/login/**","api/v1/auth/register/**").permitAll()
                        .requestMatchers("auth/activationaccount/**","auth/sifremiunuttum/**","auth/forgetpassword/**").permitAll()
                        .requestMatchers("api/v1/auth/activationaccount/**","api/v1/auth/sifremiunuttum/**","api/v1/auth/forgetpassword/**").permitAll()

                        .requestMatchers("/api/v1/auth/findall").hasAuthority("USER")
                        .requestMatchers("/api/v1/auth/softdelete").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtTokenFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());
				/*.cors(httpSecurityCorsConfigurer ->
						      httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource));
*/		return httpSecurity.build();

    }
}
