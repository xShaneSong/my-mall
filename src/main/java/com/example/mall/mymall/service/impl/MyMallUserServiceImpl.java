package com.example.mall.mymall.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mall.mymall.api.param.MallUserUpdateParam;
import com.example.mall.mymall.common.MallException;
import com.example.mall.mymall.common.ServiceResultEnum;
import com.example.mall.mymall.entity.MallUser;
import com.example.mall.mymall.entity.MallUserToken;
import com.example.mall.mymall.mapper.MallUserMapper;
import com.example.mall.mymall.mapper.MallUserTokenMapper;
import com.example.mall.mymall.service.MyMallUserService;
import com.example.mall.mymall.utils.NumberUtils;
import com.example.mall.mymall.utils.SystemUtils;

@Service
public class MyMallUserServiceImpl implements MyMallUserService {
    @Autowired
    private MallUserMapper mallUserMapper;
    @Autowired
    private MallUserTokenMapper mallUserTokenMapper;

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

    @Override
    public Boolean logout(Long userId) {
        // return mallUserTokenMapper.deleteByPrimaryKey(userId) > 0;
        return false;
    }

    @Override
    public Boolean updateUserInfo(MallUserUpdateParam mallUser, Long userId) {
        // MallUser user = mallUserMapper.selectByPrimaryKey(userId);
        // if (user == null) {
        //     MallException.fail(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        // }
        // user.setNickName(mallUser.getNickName());
        // //user.setPasswordMd5(mallUser.getPasswordMd5());
        // //若密码为空字符，则表明用户不打算修改密码，使用原密码保存
        // if (!MD5Util.MD5Encode("", "UTF-8").equals(mallUser.getPasswordMd5())){
        //     user.setPasswordMd5(mallUser.getPasswordMd5());
        // }
        // user.setIntroduceSign(mallUser.getIntroduceSign());
        // if (mallUserMapper.updateByPrimaryKeySelective(user) > 0) {
        //     return true;
        // }
        return false;
    }

    private String getNewToken(String timeString, long userId) {
        String src = timeString + userId + NumberUtils.genRandomNum(4);
        return SystemUtils.genToken(src);
    }
    
}
