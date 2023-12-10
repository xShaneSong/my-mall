package com.example.mall.mymall.api.param;

import io.swagger.annotations.ApiModelProperty;

public class MallUserUpdateParam {
    
    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户密码(需要MD5加密)")
    private String passwordMd5;

    @ApiModelProperty("个性签名")
    private String introduceSign;
}
