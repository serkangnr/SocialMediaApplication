package com.serkanguner.service;

import com.serkanguner.domain.Post;
import com.serkanguner.dto.request.PostElasticRequestDto;
import com.serkanguner.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    //@RabbitListener(queues = "q.F") Tum postlari bir kez cekmek icin kullanilir.
    @RabbitListener(queues = "q.D")
    public void save(PostElasticRequestDto dto) {
        Post post = Post.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .photo(dto.getPhoto())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .status(dto.getStatus())
                .updateAt(dto.getUpdateAt())
                .build();
        postRepository.save(post);
    }

    @RabbitListener(queues = "q.Z")
    public void update(PostElasticRequestDto dto) {
        Post post = Post.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .photo(dto.getPhoto())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .status(dto.getStatus())
                .updateAt(dto.getUpdateAt())
                .build();
        postRepository.save(post);
    }


    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }


    @RabbitListener(queues = "q.Y")
    public List<Post> findById(String userId) {
        List<Post> postList = new ArrayList<>();
        Post post = postRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        postList.add(post);
        return postList;

    }


}
