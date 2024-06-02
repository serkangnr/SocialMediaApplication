package com.serkanguner.dto.request;

import com.serkanguner.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class PostElasticRequestDto implements Serializable {
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
