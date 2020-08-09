package com.power.article.service;

import com.power.article.pojo.Comment;
import com.power.article.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.*;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(String commentId) {
        /*Optional<Comment> optional = commentRepository.findById(commentId);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;*/
        //查询条件设置
        /*Query query = new Query();
        query.addCriteria(Criteria.where("c_id").is(commentId));
        return mongoTemplate.findOne(query,Comment.class);*/
        return commentRepository.findById(commentId).get();
    }


    //新增
    public void save(Comment comment) {
        //id
        String id= idWorker.nextId()+"";
        //初始化点赞数据,发布时间等
        //comment.setC_id(id);
        comment.set_id(id);
        comment.setThumbup(0);
        comment.setPublishdate(new Date());
        //新增
        commentRepository.save(comment);
    }

    //修改
    public void updateById(Comment comment) {
        commentRepository.save(comment);
    }

    //删除
    public void deleteById(String commentId) {
        commentRepository.deleteById(commentId);
    }


    public Page<Comment> findAll(Example<Comment> example, Pageable pageable) {
        return commentRepository.findAll(example, pageable);
    }

    public List<Comment> findByArticleId(String articleId) {
        //查询条件设置
        return commentRepository.findByArticleid(articleId);
    }

    //点赞数+1
    public void addThumbup(String commentId) {
        /*//根据评论id查询评论
        Comment comment = commentRepository.findById(commentId).get();
        //修改点赞数
        comment.setThumbup(comment.getThumbup()+1);
        //修改
        commentRepository.save(comment);*/
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(commentId));

        Update update=new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query, update, "comment");
    }
}
