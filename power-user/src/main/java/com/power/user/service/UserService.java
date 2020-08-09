package com.power.user.service;

import com.power.user.dao.UserDao;
import com.power.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User fidnById(String id){
        return userDao.selectById(id);
    }
}
