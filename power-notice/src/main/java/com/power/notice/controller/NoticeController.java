package com.power.notice.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.power.notice.pojo.Notice;
import com.power.notice.pojo.NoticeFresh;
import com.power.notice.service.NoticeService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

@RestController
@RequestMapping("/notice")
@CrossOrigin
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private IdWorker idWorker;

    // notice/{id} 根据id查询
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        Notice notice=noticeService.findById(id);
        return new Result(true, StatusCode.OK,"根据id查询成功",notice);
    }

    // notice/search/{page}/{size}
    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@PathVariable Integer page,
                             @PathVariable Integer size,
                             @RequestBody Notice notice){
        Page<Notice> pageData = noticeService.findByPage(page, size, notice);
        PageResult<Notice> pageResult=new PageResult(pageData.getTotal(),pageData.getRecords());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    //新增 /notice/save
    @PostMapping("/save")
    public Result save(@RequestBody Notice notice){


        boolean flag=noticeService.save(notice);
        if(flag){
            return new Result(flag,StatusCode.OK,"新增成功");
        }else{
            return new Result(flag,StatusCode.REPERROR,"新增失败");
        }
    }

    //修改 /notice
    @PutMapping("")
    public Result updateById(@RequestBody Notice notice){
        boolean flag=noticeService.updateById(notice);
        if(flag){
            return new Result(flag,StatusCode.OK,"修改成功");
        }else{
            return new Result(flag,StatusCode.REPERROR,"修改失败");
        }
    }

    //根据用户id查询待推送消息 /notice/fresh/{userId}/{page}/{size}
    @GetMapping("/fresh/{userId}/{page}/{size}")
    public Result fresh(@PathVariable String userId,
                        @PathVariable Integer page,
                        @PathVariable Integer size){
        Page<NoticeFresh> pageData=noticeService.fresh(page,size,userId);

        PageResult<NoticeFresh> pageResult=new PageResult<>(pageData.getTotal(),pageData.getRecords());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);

    }

}
