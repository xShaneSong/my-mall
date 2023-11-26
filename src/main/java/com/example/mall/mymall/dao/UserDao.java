package com.example.mall.mymall.dao;

import java.util.List;
import com.example.mall.mymall.entity.User;

public interface UserDao {
    
    List<User> findAllUsers();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
