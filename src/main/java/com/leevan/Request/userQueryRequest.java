package com.leevan.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.dto
 * @className:      userQueryDto
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/29 16:49
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userQueryRequest {

//    前端传给后端的查询对象和登录对象
    private Integer currentPage;

    private Integer pageSize;

    private Integer id;

    private String userName;

    private String nickName;

    private String passWord;

    private String email;

    private String identity;

    private String sex;

    private String phonenumber;

    private String avatar;

    private Integer pageNum;

    private String epc;//rfid卡编号









}