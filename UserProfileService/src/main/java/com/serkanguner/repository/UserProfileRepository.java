package com.serkanguner.repository;

import com.serkanguner.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    Optional<UserProfile> findByAuthId(Long authId);
    Optional<UserProfile> findByUsernameIgnoreCase(String username);
}
