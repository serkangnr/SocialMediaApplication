package com.serkanguner.dto.request;

import com.serkanguner.constant.Status;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;


@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
public class PostElasticRequestDto implements Serializable {
    private final DateFormat dateFormat;
    String id;
    String userId;
    String title;
    String content;
    String photo;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
    @Builder.Default
    LocalDateTime updateAt = LocalDateTime.now();
    @Builder.Default
    Status status = Status.ACTIVE;

}
