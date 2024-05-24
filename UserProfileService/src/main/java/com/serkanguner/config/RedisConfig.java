package com.serkanguner.config;

import com.serkanguner.dto.request.UserProfileSaveRequestDto;
import com.serkanguner.dto.request.UserProfileSaveRequestRedisDto;
import com.serkanguner.entity.UserProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
public class RedisConfig {
    @Value("${userservice.redis.host}")
    String redisHost;
    @Value("${userservice.redis.port}")
    int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }
    //Value kısmında Object tipi yerine Product tipini deneyelim. Herhangi bir değişiklik gözlemlenmedi.
    @Bean
    public RedisTemplate<String, UserProfile> redisTemplate() {
        RedisTemplate<String,UserProfile> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, UserProfileSaveRequestRedisDto> redisTemplateRedisDto() {
        RedisTemplate<String,UserProfileSaveRequestRedisDto> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

}
