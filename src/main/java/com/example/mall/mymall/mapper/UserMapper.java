package com.example.mall.mymall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mall.mymall.entity.User;

@Mapper
public interface UserMapper {
    
    List<User> findAllUsers();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
