package com.power.article.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.power.article.pojo.Article;
import com.power.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //异常处理
    @GetMapping("/execption")
    public Result execption(){
        int a=1/0;
        return null;
    }

    // POST article/search/{page}/{size}
    @PostMapping("/search/{page}/{size}")
    public Result findByPage(@PathVariable Integer page,
                         @PathVariable Integer size,
                         @RequestBody Map<String,Object> map){
        //根据分页进行查询
        Page<Article> articlePage=articleService.findByPage(map,page,size);
        //封装分页返回对象
        PageResult<Article> pageResult=new PageResult<>(articlePage.getTotal(),articlePage.getRecords());

        //返回数据
        return new Result(true,StatusCode.OK,"查询分页",pageResult);
    }

    //GET article/{articleId} 根据文章ID查询
    @GetMapping("/{articleId}")
    public Result articleId(@PathVariable String articleId){
        return new Result(true,StatusCode.OK,"根据ID查询成功",articleService.findById(articleId));
    }

    //GET article/ 查询所有文章
    @RequestMapping(method = RequestMethod.GET)
    public Result getAll(){
        List<Article> list=articleService.findAll();
        if(list==null){
            return new Result(true,StatusCode.ERROR,"查询失败",list);
        }else{
            return new Result(false, StatusCode.OK,"查询成功",list);
        }
    }

    //POST article/ 新增文章
    @PostMapping("")
    public Result insert(@RequestBody Article article){
        return new Result(true,StatusCode.OK,"新增成功",articleService.insert(article));
    }

    //PUT article/{articleId} 修改文章
    @PutMapping("{articleId}")
    public Result updateById(@PathVariable String articleId,@RequestBody Article article ){
        //设置id
        article.setId(articleId);
        //执行修改
        boolean flag=articleService.updateById(article);
        if(flag){
            return new Result(true,StatusCode.OK,"修改成功");
        }
        return new Result(false,StatusCode.ERROR,"修改失败");
    }

    //DELETE article/{articleId} 根据id删除文章
    @DeleteMapping("{articleId}")
    public Result deleteById(@PathVariable String articleId){
        boolean flag=articleService.deleteById(articleId);
        if(flag){
            return new Result(true,StatusCode.OK,"删除成功");
        }else {
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }
}
