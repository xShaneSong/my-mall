package com.example.mall.mymall.service;

import com.example.mall.mymall.api.param.MallUserUpdateParam;

public interface MyMallUserService {
    
    String login(String loginName, String passwordMd5);
    Boolean logout(Long userId);
    Boolean updateUserInfo(MallUserUpdateParam mallUser, Long userId);
}
