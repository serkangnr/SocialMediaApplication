package com.serkanguner.domain;

import com.serkanguner.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(indexName = "post-index" )
public class Post {
    @Id
    String id;
    String userId;
    String title;
    String content;
    String photo;
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updateAt = LocalDateTime.now();
    Status status = Status.ACTIVE;
}