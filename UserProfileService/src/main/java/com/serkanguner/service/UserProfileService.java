package com.serkanguner.service;

import com.serkanguner.constant.Status;
import com.serkanguner.dto.request.*;
import com.serkanguner.entity.UserProfile;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.exception.UserServiceException;
import com.serkanguner.manager.AuthManager;
import com.serkanguner.manager.PostManager;
import com.serkanguner.mapper.UserProfileMapper;
import com.serkanguner.repository.UserProfileRepository;
import com.serkanguner.utility.JwtTokenManager;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthManager authManager;
    private final PostManager postManager;
    private final RedisTemplate<String, UserProfile> redisTemplate;
    private final RedisTemplate<String, UserProfileSaveRequestRedisDto> redisTemplateRedisDto;

    private static final String KEY = "UserProfileList";
    private static final String KEY_REDIS = "UserProfileListRedis";

    @RabbitListener(queues = "q.A")
    public void save(UserProfileSaveRequestDto dto) {
        userProfileRepository.save(UserProfileMapper.INSTANCE.dtoToUserProfile(dto));

//        UserProfileSaveRequestRedisDto requestRedisDto = UserProfileSaveRequestRedisDto.builder()
//                .username(dto.getUsername())
//                .email(dto.getEmail())
//                .authId(dto.getAuthId()).build();

        UserProfile userProfile = UserProfile.builder().username(dto.getUsername())

                .email(dto.getEmail())
                .authId(dto.getAuthId())
                .build();


        redisTemplate.opsForList().rightPush(KEY, userProfile);
    }

    public void update(UserProfileUpdateRequestDto dto) {
        /*
        Guncelleme isleminde oncelikle kayit getirilir.
         */
        UserProfile userProfile = userProfileRepository.findById(dto.getId())
                .orElseThrow(() -> new UserServiceException(ErrorType.INVALID_USER_ID));

        // bu adimda hata firlatmiyorsa userprofile olusmustur.
        //Artik dto icinden gelen bilgileri bu userprofile'in ilgili alanlarini set eder.
        userProfile.setAbout(dto.getAbout());
        userProfile.setPhoto(dto.getPhoto());
        userProfile.setPhone(dto.getPhone());
        //gerekli islemler tamamlandiktan sonra repository ile save metodu ile guncelleme yapacagiz.
        // save metodu eger icinde id varsa guncelleme , yoksa ekleme yapar
        userProfileRepository.save(userProfile);

    }


    public Boolean activateUserProfile(Long authId) {
        UserProfile userProfile = userProfileRepository.findByAuthId(authId)
                .orElseThrow(() -> new UserServiceException(ErrorType.INVALID_USERPROFILE_ID));
        userProfile.setStatus(Status.ACTIVE);
        userProfileRepository.save(userProfile);
        if (!userProfile.getStatus().equals(Status.ACTIVE)) {
            throw new UserServiceException(ErrorType.USERPROFILE_UPDATE_STATUS_FAILED);
        }
        return true;
    }


    @Transactional
    public void updateUserProfile(UserProfileUpdateRequestDto dto) {
        Long authId =
                jwtTokenManager.getIdFromToken(dto.getToken())
                        .orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));

        UserProfile userProfile = userProfileRepository.findByAuthId(authId)
                .orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
        if (dto.getEmail() != null) {
            userProfile.setEmail(dto.getEmail());
            //authda da değişmeli.
            authManager.updateEmail(authId, dto);
        }
        if (dto.getPhone() != null) {
            userProfile.setPhone(dto.getPhone());
        }
        if (dto.getPhoto() != null) {
            userProfile.setPhoto(dto.getPhoto());
        }
        if (dto.getAddress() != null) {
            userProfile.setAddress(dto.getAddress());
        }
        if (dto.getAbout() != null) {
            userProfile.setAbout(dto.getAbout());
        }
        userProfileRepository.save(userProfile);
    }


    @RabbitListener(queues = "q.Delete")
    public void delete(Long authId) {
        UserProfile userProfile = userProfileRepository.findByAuthId(authId)
                .orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
        userProfile.setStatus(Status.DELETED);
        userProfileRepository.save(userProfile);
    }

    public String getUserIdToken(Long authId) {
        UserProfile userProfile = userProfileRepository.findByAuthId(authId).orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
        return jwtTokenManager.createTokenForUserId(authId, userProfile.getId()).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));
    }

    // repository ile findbUsername
    public UserProfile getUserProfileListByUsername(String username) {
        return userProfileRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
    }

    @PostConstruct
    private void init() {
        if (!redisTemplate.hasKey(KEY)) {
            List<UserProfile> allUserProfile = userProfileRepository.findAll();
            allUserProfile.forEach(userProfile -> redisTemplate.opsForList().rightPush(KEY,
                    userProfile));
        }
    }

    //ada gore cache uzerinden arama
    public List<UserProfile> getUserProfileByUsername(String username) {

        return findAllCache().stream().filter(userProfile -> userProfile.getUsername().equals(username)).collect(Collectors.toList());
    }

    private List<UserProfile> findAllCache() {
        return redisTemplate.opsForList().range(KEY, 0, -1);
    }


    public List<UserProfile> getUserProfileByStatus(Status status) {
        return findAllCache().stream().filter(userProfile -> userProfile.getStatus().equals(status)).collect(Collectors.toList());
    }

    public List<UserProfile> findAll(){
        return userProfileRepository.findAll();
    }


    public UserProfile findByAuthId(Long authid) {
        UserProfile userProfile = userProfileRepository.findByAuthId(authid).orElseThrow(() -> new UserServiceException(ErrorType.USER_NOT_FOUND));
        return userProfile;
    }
}
