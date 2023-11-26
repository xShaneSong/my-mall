package com.example.mall.mymall.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mall.mymall.common.ServiceResultEnum;
import com.example.mall.mymall.entity.MallUser;
import com.example.mall.mymall.entity.MallUserToken;
import com.example.mall.mymall.mapper.MallUserMapper;
import com.example.mall.mymall.mapper.MyMallUserTokenMapper;
import com.example.mall.mymall.service.MyMallUserService;
import com.example.mall.mymall.utils.NumberUtils;
import com.example.mall.mymall.utils.SystemUtils;

@Service
public class MyMallUserServiceImpl implements MyMallUserService {
    @Autowired
    private MallUserMapper mallUserMapper;
    @Autowired
    private MyMallUserTokenMapper mallUserTokenMapper;

    @Override
    public String login(String loginName, String passwordMd5) {
        MallUser user = mallUserMapper.selectByLoginNameAndPassword(loginName, passwordMd5);
        if (user != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED_ERROR.getResult();
            }
            String token = getNewToken(System.currentTimeMillis() + "", user.getUserId());
            MallUserToken mallUserToken = mallUserTokenMapper.selectByPrimaryKey(user.getUserId());
            Date now = new Date();
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);
            if (mallUserToken == null) {
                mallUserToken = new MallUserToken();
                mallUserToken.setUserId(user.getUserId());
                mallUserToken.setToken(token);
                mallUserToken.setUpdateTime(now);
                mallUserToken.setExpireTime(expireTime);
                if (mallUserTokenMapper.insertSelective(mallUserToken) > 0) {
                    return token;
                }
            } else {
                mallUserToken.setToken(token);
                mallUserToken.setUpdateTime(now);
                mallUserToken.setExpireTime(expireTime);
                if (mallUserTokenMapper.updateByPrimaryKeySelective(mallUserToken) > 0) {
                    return token;
                }
            }
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult(); 
    }

    private String getNewToken(String timeString, long userId) {
        String src = timeString + userId + NumberUtils.genRandomNum(4);
        return SystemUtils.genToken(src);
    }
    
}
