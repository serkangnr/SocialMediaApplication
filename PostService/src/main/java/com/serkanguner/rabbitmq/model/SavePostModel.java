package com.serkanguner.rabbitmq.model;

import com.serkanguner.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Onemli modeller mutlaka serilestirelebilir olmalidir.
 * Ayrica paket ismi dahil olmak uzere bu sinifin aynisi iletilen servistede olmalidir.
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SavePostModel implements Serializable {
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
