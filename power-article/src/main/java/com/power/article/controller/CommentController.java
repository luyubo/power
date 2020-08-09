package com.power.article.controller;

import com.power.article.pojo.Comment;
import com.power.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    //GET /comment 查询所有评论
    @GetMapping("")
    public Result findAll(){
        List<Comment> commentList=commentService.findAll();

        return new Result(true, StatusCode.OK,"查询所有评论成功",commentList);
    }

    //查询条件,可以传递多个查询条件
    @GetMapping("/page/{page}/{size}")
    public Result page(@PathVariable Integer page,
                       @PathVariable Integer size,
                        @RequestBody Comment comment){
        Example<Comment> example = Example.of(comment);

        //分页条件
        //Pageable pageable = new PageRequest(pageNUmber,pageSize);
        Pageable pageable = PageRequest.of(page,size);
        return new Result(true,StatusCode.OK,"查询成功",commentService.findAll(example,pageable));
    }

    //GET /comment/{commentId} 根据评论id查询评论
    @GetMapping("/{commentId}")
    public Result findById(@PathVariable String commentId){
        return new Result(true,StatusCode.OK,"查询评论成功",commentService.findById(commentId));
    }

    //POST 新增 /comment
    @PostMapping("")
    public Result save(@RequestBody Comment comment){
        System.out.println(comment);
        commentService.save(comment);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    //PUT /comment/{commentId} 根绝文章id修改
    @PutMapping("/{commentId}")
    public Result updateById(@PathVariable String commentId,
                             @RequestBody Comment comment){
        comment.set_id(commentId);
        commentService.updateById(comment);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    //DELETE /comment/{commentId} 根据评论id删除数据
    @DeleteMapping("/{commentId}")
    public Result deleteById(@PathVariable String commentId){
        commentService.deleteById(commentId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    // GET /comment/article/{articleId} 根据文章id查询评论
    @GetMapping("/article/{articleId}")
    public Result findByArticleId(@PathVariable String articleId){
        return new Result(true,StatusCode.OK,"根据文章id查询成功",commentService.findByArticleId(articleId));
    }

    //PUT /comment/thumbup/{commentId} //根据评论id修改点赞数
    @PutMapping("/thumbup/{commentId}")
    public Result addThumbup(@PathVariable String commentId){
        //用户点赞信息存入到redis中,如果没有用户点赞信息可以点赞,
        //如果有不能点赞
        String userId="123";
        Object flag = redisTemplate.opsForValue().get("thumbup_" + userId + "_" + commentId);
        if(flag==null){
            //点赞新增
            commentService.addThumbup(commentId);
            //redis记录点赞状态
            redisTemplate.opsForValue().set("thumbup_" + userId + "_" + commentId,1);
            //返回状态
            return new Result(true,StatusCode.OK,"新增点赞成功");
        }
        //返回失败状态
        return new Result(false,StatusCode.REPERROR,"新增点赞失败");
    }
}
