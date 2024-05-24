package com.serkanguner.entity;

import com.serkanguner.constant.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document // MongoDB icin document kullanilmasi gerekli
public class UserProfile  {
    @MongoId
    String id;
    Long authId;
    String username;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    @Builder.Default
    Status status = Status.PENDING;




}
