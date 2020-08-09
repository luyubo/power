package com.power.article.repository;

import com.power.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String> {
    //根据id查询
    Comment findBy_id(String id);
    //根据文章id查询
    List<Comment> findByArticleid(String articleId);
    //根据时间查询
    List<Comment> findAllByPublishdateAfter(Date date);
}
