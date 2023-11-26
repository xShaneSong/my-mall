package com.example.mall.mymall.entity;

import java.util.Date;
import lombok.Data;

@Data
public class MallUserToken {
    private Long userId;
    private String token;
    private Date updateTime;
    private Date expireTime;
}
