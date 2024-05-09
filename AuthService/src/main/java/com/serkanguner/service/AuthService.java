package com.serkanguner.service;

import com.serkanguner.dto.request.LoginRequestDto;
import com.serkanguner.dto.request.RegisterRequestDto;
import com.serkanguner.dto.response.LoginResponseDto;
import com.serkanguner.entity.Auth;
import com.serkanguner.exception.AuthMicroServiceException;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.mapper.AuthMapper;
import com.serkanguner.repository.AuthRepository;
import com.serkanguner.utility.JwtTokenManager;
import com.serkanguner.utility.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final TokenManager tokenManager;
    private final JwtTokenManager jwtTokenManager;

    public Auth save(RegisterRequestDto dto) {
        if (!dto.getPassword().equals(dto.getRepassword())) {
            throw new AuthMicroServiceException(ErrorType.PASSWORD_AND_REPASSWORD_NOT_EQUALS);
        }
        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthMicroServiceException(ErrorType.USERNAME_ALREADY_TAKEN);
        }
        Auth auth = AuthMapper.INSTANCE.dtoToAuth(dto);
//        auth.setCreateAt(LocalDateTime.now());
//        auth.setState(true);
        return authRepository.save(auth);
    }

    public Auth findOptionalByUsernameAndPassword(String username, String password) {
        return authRepository.findOptionalByUsernameAndPassword(username,
                        password)
                .orElseThrow(() -> new AuthMicroServiceException(ErrorType.USERNAME_OR_PASSWORD_WRONG));
    }


    /**
     * Username ve password ile dogrulama islemi yapar
     * Eger dogrulama basarisiz olursa hata firlatir
     * Eger dogrulama basarili olursa bir kimlik verelim
     *
     * @param dto
     * @return
     */

    public String doLogin(LoginRequestDto dto) {
        Auth auth = findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        //return tokenManager.createToken(auth.getId());
        return jwtTokenManager.createToken(auth.getId()).get();
    }


    public List<Auth> findAll(String token) {
        Long idFromToken = null;

        idFromToken = jwtTokenManager
                .getIdFromToken(token)
                .orElseThrow(() ->
                        new AuthMicroServiceException(ErrorType.INVALID_TOKEN));
        authRepository.findById(idFromToken).orElseThrow(() -> new AuthMicroServiceException(ErrorType.INVALID_TOKEN));

        return authRepository.findAll();


    }
}
