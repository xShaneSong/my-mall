package com.example.mall.mymall.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class MallUser {
    private Long userId;
    private String nickName;
    private String passwordMd5;
    private String introduceSign;
    private Byte isDeleted;
    private Byte lockedFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
