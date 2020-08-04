package com.power.article.repository;

import com.power.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String> {

}
