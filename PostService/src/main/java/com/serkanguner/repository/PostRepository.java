package com.serkanguner.repository;

import com.serkanguner.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByUserId(String userId);

   // List<Post> findAllByAuthId(long authId);
}
