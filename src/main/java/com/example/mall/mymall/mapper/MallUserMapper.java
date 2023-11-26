package com.example.mall.mymall.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.mall.mymall.entity.MallUser;

public interface MallUserMapper {
    MallUser selectByLoginNameAndPassword(
        @Param("loginName") String loginName,
        @Param("password") String password);
}
