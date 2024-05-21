package com.serkanguner.entity;

import com.serkanguner.constant.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document // MongoDB icin document kullanilmasi gerekli
public class Post {
    @MongoId
    String id;
    String userId;
    String title;
    String content;
    String photo;
    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    LocalDateTime updateAt = LocalDateTime.now();
    @Builder.Default
    Status status = Status.ACTIVE;

}
