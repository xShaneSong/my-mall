package com.example.mall.mymall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.mall.mymall.dao.UserDao;
import com.example.mall.mymall.entity.User;
import com.example.mall.mymall.mapper.UserMapper;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;

import java.util.List;

@RestController
public class MyBatisController {
    
    @Resource
    UserMapper userDao;

    @GetMapping("/users/mybatis/queryAll")
    public List<User> queryAll() {
        return userDao.findAllUsers();
    }

    @GetMapping("/users/mybatis/insert")
    public Boolean insert(String name, String password) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return false;
        }

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userDao.insertUser(user) >= 1 ? true : false;
    }

    @GetMapping("/users/mybatis/update")
    public Boolean update(Integer id, String name, String password) {
        if (id == null || id < 1 || StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return false;
        }

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        return userDao.updateUser(user) >= 1 ? true : false;

    }

    @GetMapping("/users/mybatis/delete")
    public Boolean delete(Integer id) {
        if (id == null || id < 1) {
            return false;
        }
        return userDao.deleteUser(id) >= 1 ? true : false;
    }
}
