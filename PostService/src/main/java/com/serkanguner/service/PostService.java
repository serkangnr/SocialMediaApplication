package com.serkanguner.service;

import com.serkanguner.constant.Status;
import com.serkanguner.dto.request.PostSaveRequestDto;
import com.serkanguner.dto.request.PostUpdateRequestDto;
import com.serkanguner.entity.Post;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.exception.UserServiceException;
import com.serkanguner.manager.UserProfileManager;
import com.serkanguner.mapper.PostMapper;
import com.serkanguner.repository.PostRepository;
import com.serkanguner.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JwtTokenManager jwtTokenManager;
    private final UserProfileManager userProfileManager;
    private final PostMapper postMapper;


    public void save(PostSaveRequestDto dto) {
        postRepository.save(PostMapper.INSTANCE.dtoToPost(dto));
    }

    @Transactional
    public void savePost(String token, PostSaveRequestDto dto) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));
        Post post = postMapper.dtoToPost(dto);
        post.setUserId(jwtTokenManager.getUserIdFromToken(userProfileManager.getUserIdToken(authId)).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN)));
        postRepository.save(post);
    }

    @Transactional
    public List<Post> findAll(String token) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));
        jwtTokenManager.getUserIdFromToken(userProfileManager.getUserIdToken(authId)).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));
        return postRepository.findAll();
    }

    @Transactional
    public List<Post> findByToken(String token) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));
        String userId = jwtTokenManager.getUserIdFromToken(userProfileManager.getUserIdToken(authId)).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));

        return postRepository.findByUserId(userId);
    }

    @Transactional
    public void deletePost(String token, Long postId) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));
        String userId = jwtTokenManager.getUserIdFromToken(userProfileManager.getUserIdToken(authId)).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));

        Post post = postRepository.findById(String.valueOf(postId)).orElseThrow(() -> new UserServiceException(ErrorType.POST_NOT_FOUND));

        if (post.getUserId().equals(userId)) {
            if (post.getStatus().equals(Status.DELETED)) {
                throw new UserServiceException(ErrorType.POST_ALREADY_DELETED);
            }
            post.setStatus(Status.DELETED);
        }
        postRepository.save(post);
    }

    @Transactional
    public void update(String token, PostUpdateRequestDto dto) {
        Long authId = jwtTokenManager.getAuthIdFromToken(token).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));
        String userId = jwtTokenManager.getUserIdFromToken(userProfileManager.getUserIdToken(authId)).orElseThrow(() -> new UserServiceException(ErrorType.INVALID_TOKEN));

        Post post = postRepository.findById(String.valueOf(dto.getPostId())).orElseThrow(() -> new UserServiceException(ErrorType.POST_NOT_FOUND));

        if (post.getUserId().equals(userId)) {
            if (post.getStatus().equals(Status.DELETED)) {
                throw new UserServiceException(ErrorType.POST_ALREADY_DELETED);
            }
           if (dto.getPhoto()!= null) {
               post.setPhoto(dto.getPhoto());
           }
            if (dto.getTitle()!= null) {
                post.setTitle(dto.getTitle());
            }
            if (dto.getContent()!= null) {
                post.setContent(dto.getContent());
            }
            post.setUpdateAt(LocalDateTime.now());
        }
        postRepository.save(post);
    }
}
