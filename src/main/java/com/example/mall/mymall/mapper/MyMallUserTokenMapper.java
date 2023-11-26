package com.example.mall.mymall.mapper;

import com.example.mall.mymall.entity.MallUserToken;

public interface MyMallUserTokenMapper {
    int insertSelective(MallUserToken record);
    MallUserToken selectByPrimaryKey(Long userId);
    MallUserToken selectByToken(String token);
    int updateByPrimaryKeySelective(MallUserToken record);
}
