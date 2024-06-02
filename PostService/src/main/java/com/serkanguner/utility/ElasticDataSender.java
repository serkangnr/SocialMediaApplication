package com.serkanguner.utility;


import com.serkanguner.dto.request.PostElasticRequestDto;
import com.serkanguner.entity.Post;
import com.serkanguner.service.PostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ElasticDataSender {
    private final RabbitTemplate rabbitTemplate;
    private final PostService postService;

   // @PostConstruct
    public void send(){
        List<Post> allPosts = postService.findAll();
        allPosts.forEach(post -> {
            PostElasticRequestDto dto = new PostElasticRequestDto(
                 post.getId(),
                    post.getUserId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getPhoto(),
                    post.getCreatedAt(),
                    post.getUpdateAt(),
                    post.getStatus()
            );
            rabbitTemplate.convertAndSend("exchange.direct","Routing.F",dto);
        });

    }
}
/*public class ElasticDataSender {
    private final RabbitTemplate rabbitTemplate;
    private final UserProfileService userProfileService;

    @PostConstruct
    public void send(){
        List<UserProfile> allUserProfiles = userProfileService.findAll();
        allUserProfiles.forEach(userProfile -> {
            UserProfileRequestDto dto = new UserProfileRequestDto(
                    userProfile.getId(),
                    userProfile.getAuthId(),
                    userProfile.getUsername(),
                    userProfile.getEmail(),
                    userProfile.getPhoto(),
                    userProfile.getPhone(),
                    userProfile.getWebsite(),
                    userProfile.getAbout()
            );
            elasticUserProfileManager.save(dto);
        });
    }
}*/
