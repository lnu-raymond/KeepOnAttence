package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      menu
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/29 10:01
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class menu {

    private Integer id;

    private String menuName;

    private String path;

    private String visible;

    private String status;

    private String icon;

    private Integer pid;

    private List<menu> children;



}