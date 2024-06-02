package com.serkanguner.controller;

import com.serkanguner.domain.Post;
import com.serkanguner.dto.request.PostElasticRequestDto;
import com.serkanguner.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.serkanguner.constant.EndPoints.*;

@RestController
@RequestMapping(USERPROFILE)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody PostElasticRequestDto dto) {
        postService.save(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Void> update(@RequestBody PostElasticRequestDto dto) {
        postService.save(dto);
        return ResponseEntity.ok().build();

    }

    @GetMapping(FINDALL)
    public ResponseEntity<Iterable<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<List<Post>> findById(@RequestParam(name = "id") String userId) {
        return ResponseEntity.ok(postService.findById(userId));
    }
}
