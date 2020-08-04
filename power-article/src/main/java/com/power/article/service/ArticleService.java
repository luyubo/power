package com.power.article.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.power.article.dao.ArticleDao;
import com.power.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;


    public List<Article> findAll() {
        return articleDao.selectList(null);
    }

    public Article findById(String id) {
        return articleDao.selectById(id);
    }

    public Object insert(Article article) {
        article.setId(idWorker.nextId()+"");

        article.setThumbup(0);
        article.setVisits(0);
        article.setComment(0);
        return articleDao.insert(article);
    }

    public boolean updateById(Article article) {
        //根据主键id修改
        Integer i =0;
        //i= articleDao.updateById(article);

        //根据多条件修改
        //创建条件对象
        EntityWrapper<Article> wrapper=new EntityWrapper<Article>();
        wrapper.eq("id",article.getId());
        i=articleDao.update(article,wrapper);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteById(String articleId) {
        Integer i=0;
        //直接删除
        //i= articleDao.deleteById(articleId);

        //根据条件删除
        Article article = articleDao.selectById(articleId);
        EntityWrapper<Article> wrapper=new EntityWrapper<>();
        wrapper.eq("id",wrapper);
        i = articleDao.delete(wrapper);
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public Page<Article> findByPage(Map<String, Object> map, Integer page, Integer size) {
        //设置查询条件
        EntityWrapper<Article> wrapper=new EntityWrapper<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            /*if(map.get(key)!=null){
                wrapper.eq(key,map.get(key));
            }*/
            wrapper.eq(map.get(key)!=null,key,map.get(key));
        }
        //设置分页步骤
        Page<Article> articlePage=new Page(page,size);
        //查询结果
        List<Article> articleList = articleDao.selectPage(articlePage, wrapper);
        articlePage.setRecords(articleList);
        //返回
        return articlePage;
    }
}
