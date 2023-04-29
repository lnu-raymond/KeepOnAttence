package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      user
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/24 14:29
 * @version:    1.0
 */ 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    private Integer id;

    private String userName;

    private String nickName;

    private String passWord;

    private String status;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private String identity;

    private Integer createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;

    private String epc;//电子标签卡号

    private List<attendance> attendanceList;


}