package com.example.mall.mymall.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mall.mymall.api.param.MallUserLoginParam;
import com.example.mall.mymall.api.param.MallUserUpdateParam;
import com.example.mall.mymall.api.vo.MallUserVO;
import com.example.mall.mymall.common.Constants;
import com.example.mall.mymall.config.annotation.TokenToMallUser;
import com.example.mall.mymall.entity.MallUser;
import com.example.mall.mymall.service.MyMallUserService;
import com.example.mall.mymall.utils.BeanUtils;
import com.example.mall.mymall.utils.Result;
import com.example.mall.mymall.utils.ResultGenerator;

import org.springframework.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@RestController
@Api(value = "v1", tags = "user api")
@RequestMapping("/api/v1")
public class MallPersonalApi {
    
    @Resource
    private MyMallUserService myMallUserService;

    private static final Logger logger = LoggerFactory.getLogger(MallPersonalApi.class);

    @PostMapping("/user/login")
    @ApiOperation(value = "login api", notes = "retuen token")
    public Result<String> login(@RequestBody @Valid MallUserLoginParam mallUserLoginParam) {
        String loginResult = myMallUserService.login(mallUserLoginParam.getLoginName(), mallUserLoginParam.getPasswordMd5());
        logger.info("login api, loginName={}, logResult={}", mallUserLoginParam.getLoginName(), loginResult);

        if (StringUtils.hasText(loginResult) && loginResult.length() == Constants.TOKEN_LENGTH) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(loginResult);
            return result;
        }
        return ResultGenerator.genFailResult(loginResult);
    }

    @PostMapping("/user/logout")
    @ApiOperation(value = "登出接口", notes = "清除token")
    public Result<String> logout(@TokenToMallUser MallUser loginMallUser) {
        Boolean logoutResult = myMallUserService.logout(loginMallUser.getUserId());

        logger.info("logout api,loginMallUser={}", loginMallUser.getUserId());

        //登出成功
        if (logoutResult) {
            return ResultGenerator.genSuccessResult();
        }
        //登出失败
        return ResultGenerator.genFailResult("logout error");
    }


    // @PostMapping("/user/register")
    // @ApiOperation(value = "用户注册", notes = "")
    // public Result register(@RequestBody @Valid MallUserRegisterParam mallUserRegisterParam) {
    //     if (!NumberUtil.isPhone(mallUserRegisterParam.getLoginName())){
    //         return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_IS_NOT_PHONE.getResult());
    //     }
    //     String registerResult = newBeeMallUserService.register(mallUserRegisterParam.getLoginName(), mallUserRegisterParam.getPassword());

    //     logger.info("register api,loginName={},loginResult={}", mallUserRegisterParam.getLoginName(), registerResult);

    //     //注册成功
    //     if (ServiceResultEnum.SUCCESS.getResult().equals(registerResult)) {
    //         return ResultGenerator.genSuccessResult();
    //     }
    //     //注册失败
    //     return ResultGenerator.genFailResult(registerResult);
    // }

    @PutMapping("/user/info")
    @ApiOperation(value = "修改用户信息", notes = "")
    public Result updateInfo(@RequestBody @ApiParam("用户信息") MallUserUpdateParam mallUserUpdateParam, @TokenToMallUser MallUser loginMallUser) {
        Boolean flag = myMallUserService.updateUserInfo(mallUserUpdateParam, loginMallUser.getUserId());
        if (flag) {
            //返回成功
            Result result = ResultGenerator.genSuccessResult();
            return result;
        } else {
            //返回失败
            Result result = ResultGenerator.genFailResult("修改失败");
            return result;
        }
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "获取用户信息", notes = "")
    public Result<MallUserVO> getUserDetail(@TokenToMallUser MallUser loginMallUser) {
        //已登录则直接返回
        MallUserVO mallUserVO = new MallUserVO();
        BeanUtils.copyProperties(loginMallUser, mallUserVO);
        return ResultGenerator.genSuccessResult(mallUserVO);
    }

    //////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/test1")
    @ApiOperation(value = "Test Api", notes = "@TokenToMallUser in method")
    public Result<String> test1(@TokenToMallUser MallUser user) {
        Result result = null;
        if (user == null) {
            result = ResultGenerator.genErrorResult(416, "Not Loging");
        } else {
            result = ResultGenerator.genSuccessResult("login success.");
        }
        return result;
    }
    
    @GetMapping(value = "/test2")
    @ApiOperation(value = "Test Api", notes = "@TokenToMalluser not is method")
    public Result<String> test2() {
        Result result = ResultGenerator.genSuccessResult("This interface not need login");
        return result;
    }

}
