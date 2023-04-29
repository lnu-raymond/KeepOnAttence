package com.leevan.vo;

import com.leevan.pojo.menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.dto
 * @className:      userDto
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/25 15:35
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userVo {

//    后端统一返回前端的用户对象
    private Integer id;

    private String userName;

    private String nickName;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private String identity;

    private String  createTime;

    private String token;

    private String epc;

    private List<menu> menus;


}