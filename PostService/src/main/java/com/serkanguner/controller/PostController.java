package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.PostSaveRequestDto;
import com.serkanguner.dto.request.PostUpdateRequestDto;
import com.serkanguner.entity.Post;
import com.serkanguner.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(EndPoints.POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/createpost")
    public ResponseEntity<String> createPost(@RequestParam(name = "token") String token, @RequestBody PostSaveRequestDto dto){
        postService.savePost(token,dto);
        return ResponseEntity.ok("Success");
    }

    @GetMapping(EndPoints.FINDALL)
    public ResponseEntity<List<Post>> findAll(@RequestParam(name = "token") String token){
        return ResponseEntity.ok(postService.findAll(token));
    }
    @GetMapping(EndPoints.FINDBYID)
    public ResponseEntity<List<Post>> findByUserId(@RequestParam(name = "token") String token){
        return ResponseEntity.ok(postService.findByToken(token));
    }

    @DeleteMapping(EndPoints.DELETE)
    public ResponseEntity<String> deletePost(@RequestParam(name = "token") String token, @RequestParam(name = "postId") Long postId){
        postService.deletePost(token,postId);
        return ResponseEntity.ok("Success");
    }

    @PutMapping(EndPoints.UPDATE)
    public ResponseEntity<Void> update (@RequestParam(name = "token") String token, @RequestBody PostUpdateRequestDto dto){
        postService.update(token, dto);
        return ResponseEntity.ok().build();
    }



}
