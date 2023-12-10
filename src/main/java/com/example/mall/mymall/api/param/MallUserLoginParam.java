package com.example.mall.mymall.api.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MallUserLoginParam implements Serializable {

    @ApiModelProperty("Login name")
    @NotEmpty(message = "login name is not empty")
    private String loginName;

    @ApiModelProperty("Password(need md5)")
    @NotEmpty(message = "password is not empty")
    private String passwordMd5;
}