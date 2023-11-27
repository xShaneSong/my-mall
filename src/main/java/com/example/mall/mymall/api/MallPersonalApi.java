package com.example.mall.mymall.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mall.mymall.common.Constants;
import com.example.mall.mymall.service.MyMallUserService;
import com.example.mall.mymall.utils.Result;
import com.example.mall.mymall.utils.ResultGenerator;

import org.springframework.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

}
