package com.power.user.controller;

import com.power.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public Result findById(@PathVariable String userId){
        return new Result(true, StatusCode.OK,"查询成功",userService.fidnById(userId));
    }
}
