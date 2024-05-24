package com.serkanguner.service;

import com.serkanguner.constant.Role;
import com.serkanguner.constant.Status;
import com.serkanguner.dto.request.*;
import com.serkanguner.dto.response.ActivationResponseDto;
import com.serkanguner.entity.Auth;
import com.serkanguner.exception.AuthServiceException;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.manager.UserProfileManager;
import com.serkanguner.mapper.AuthMapper;
import com.serkanguner.repository.AuthRepository;
import com.serkanguner.utility.CodeGenerator;
import com.serkanguner.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final UserProfileManager userProfileManager;
    private final RabbitTemplate rabbitTemplate;


    @Transactional
    public Auth save(RegisterRequestDto dto) {
        if (!dto.getPassword().equals(dto.getRepassword())) {
            throw new AuthServiceException(ErrorType.PASSWORD_AND_REPASSWORD_NOT_EQUALS);
        }
        if (authRepository.existsByUsername(dto.getUsername())) {
            throw new AuthServiceException(ErrorType.USERNAME_ALREADY_TAKEN);
        }
        if (authRepository.existsByEmail(dto.getEmail())) {
            throw new AuthServiceException(ErrorType.EMAIL_ALREADY_TAKEN);
        }
        Auth auth = AuthMapper.INSTANCE.dtoToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateActivationCode());

        auth = authRepository.save(auth);

        UserProfileSaveRequestDto userProfileSaveRequestDto = UserProfileSaveRequestDto.builder()
                .authId(auth.getId())
                .email(auth.getEmail().toLowerCase())
                .username(auth.getUsername().toLowerCase())
                .build();

        InfoDto info = InfoDto.builder()
                .email(userProfileSaveRequestDto.getEmail().toLowerCase())
                .activationCode(auth.getActivationCode()).build();


        //userProfileManager.save(userProfileSaveRequestDto);
         rabbitTemplate.convertAndSend("exchange.direct", "Routing.A", userProfileSaveRequestDto);
        rabbitTemplate.convertAndSend("exchange.direct", "Routing.B", info);
        return auth;
    }

    public Auth findOptionalByEmailAndPassword(String email, String password) {
        return authRepository.findOptionalByEmailAndPassword(email,
                        password)
                .orElseThrow(() -> new AuthServiceException(ErrorType.EMAIL_OR_PASSWORD_WRONG));
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
        Auth auth = findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (!auth.getStatus().equals(Status.ACTIVE)) {
            throw new AuthServiceException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
        ;
        //return tokenManager.createToken(auth.getId());
        //return AuthMapper.INSTANCE.loginToDto(auth);
        return jwtTokenManager.createToken(auth.getId()).get();
    }

    public String doLoginWithRole(LoginRequestDto dto) {
        Auth auth = findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (!auth.getStatus().equals(Status.ACTIVE)) {
            throw new AuthServiceException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
        ;
        return jwtTokenManager.createDoubleToken(auth.getId(), auth.getRole()).get();
    }


    public List<Auth> findAll(String token) {
        Long idFromToken = null;

        idFromToken = jwtTokenManager
                .getIdFromToken(token)
                .orElseThrow(() ->
                        new AuthServiceException(ErrorType.INVALID_TOKEN));
        authRepository.findById(idFromToken).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));

        return authRepository.findAll();

    }

    public List<Auth> findAll2(String token) {
        Role roleFromToken = null;

        roleFromToken = jwtTokenManager
                .getRoleFromToken(token)
                .orElseThrow(() ->
                        new AuthServiceException(ErrorType.INVALID_TOKEN));
        if (!roleFromToken.equals(Role.ADMIN)) {
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }
        //authRepository.findByRole(roleFromToken).orElseThrow(()-> new AuthServiceException(ErrorType.INVALID_TOKEN));

        return authRepository.findAll();

    }


    public ActivationResponseDto activationAccount(ActivationRequestDto dto) {
        Auth auth = authRepository.findById(dto.getId()).orElseThrow(() -> new AuthServiceException(ErrorType.ACCOUNT_NOT_FOUND));


        statusControl(auth);

        if (!auth.getActivationCode().equals(dto.getActivationCode())) {
            throw new AuthServiceException(ErrorType.INVALID_ACTIVATION_CODE);
        }

        auth.setStatus(Status.ACTIVE);


        authRepository.save(auth);
        return AuthMapper.INSTANCE.activationToDto(auth);

    }

    public ActivationResponseDto activationAccountOpenFeign(ActivationRequestDto dto) {
        Auth auth = authRepository.findById(dto.getId()).orElseThrow(() -> new AuthServiceException(ErrorType.ACCOUNT_NOT_FOUND));
        statusControl(auth);

        if (!auth.getActivationCode().equals(dto.getActivationCode())) {
            throw new AuthServiceException(ErrorType.INVALID_ACTIVATION_CODE);
        }

        auth.setStatus(Status.ACTIVE);


        authRepository.save(auth);
        return AuthMapper.INSTANCE.activationToDto(auth);

    }

    @Transactional
    public String statusControl(Auth auth) {
        switch (auth.getStatus()) {
            case ACTIVE -> throw new AuthServiceException(ErrorType.ACCOUNT_ALREADY_ACTIVE);
            case PENDING -> {
                auth.setStatus(Status.ACTIVE);
                authRepository.save(auth);
                userProfileManager.activateUserProfile(auth.getId());
                return "Aktivasyon Başarılı! Sisteme giriş yapabilirsiniz.";
            }
            case BANNED -> throw new AuthServiceException(ErrorType.ACCOUNT_ALREADY_BANNED);
            case DELETED -> throw new AuthServiceException(ErrorType.ACCOUNT_ALREADY_DELETED);
            default -> throw new AuthServiceException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
    }


    public String softDelete(Long authId) {
        Auth auth = authRepository.findById(authId)
                .orElseThrow(() -> new AuthServiceException(ErrorType.ACCOUNT_NOT_FOUND));
        if (auth.getStatus().equals(Status.DELETED)) {
            throw new AuthServiceException(ErrorType.ACCOUNT_ALREADY_DELETED);
        }
        auth.setStatus(Status.DELETED);
        authRepository.save(auth);
        userProfileManager.deleteUserProfile(authId);
        return authId + " idli kullanıcı silindi.";
    }

    public String getToken(Long authId) {
        Auth auth = authRepository.findById(authId).orElseThrow(() -> new AuthServiceException(ErrorType.ACCOUNT_NOT_FOUND));
        return jwtTokenManager.createToken(auth.getId()).get();
    }

    public String getRoleToken(Long authId, Role role) {
        Auth auth = authRepository.findById(authId).orElseThrow(() -> new AuthServiceException(ErrorType.ACCOUNT_NOT_FOUND));
        return jwtTokenManager.createDoubleToken(auth.getId(), auth.getRole()).get();
    }

    public Long getIdFromToken(String token) {
        return jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));
    }

    public Role getRoleFromToken(String token) {
        return jwtTokenManager.getRoleFromToken(token).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));
    }

    public Boolean updateEmail(Long authId, UserProfileUpdateRequestDto dto) {
        Auth auth = authRepository.findById(authId).orElseThrow(() -> new AuthServiceException(ErrorType.ACCOUNT_NOT_FOUND));
        auth.setEmail(dto.getEmail());
        authRepository.save(auth);
        return true;

    }

    public String sifremiUnuttum(String email){
        String generateNewPasswordCode = CodeGenerator.generateNewPasswordCode();

        InfoDto info = InfoDto.builder()
                .email(email)
                .activationCode(generateNewPasswordCode)
                .build();

        rabbitTemplate.convertAndSend("exchange.direct", "Routing.C", info);
        return "Sifrenizi yenileme kodunuz "+email+ " adresine gonderilmistir.";
    }

    public Boolean updatePassword(Long authId, UpdatePasswordDto dto){
        Auth auth = authRepository.findById(authId).orElseThrow(() -> new AuthServiceException(ErrorType.ACCOUNT_NOT_FOUND));
        auth.setPassword(dto.getNewPassword());
        authRepository.save(auth);


        //rabbitTemplate.convertAndSend("exchange.direct", "Routing.C", info);
        return true;
    }
}
