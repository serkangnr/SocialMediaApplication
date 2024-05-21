package com.serkanguner.manager;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.PostSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:9093/api/v1/post", name = "postmanager",dismiss404 = true)
public interface PostManager {

    @PostMapping(EndPoints.SAVE)
    public ResponseEntity<String> save(@RequestBody PostSaveRequestDto dto);
}
