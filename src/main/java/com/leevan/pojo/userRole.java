package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      userRole
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/31 21:37
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userRole {
    private Integer userId;

    private Integer roleId;

    private String identity;


}