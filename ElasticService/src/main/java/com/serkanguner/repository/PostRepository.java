package com.serkanguner.repository;

import com.serkanguner.domain.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ElasticsearchRepository <Post,String> {
}
