package com.leevan.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.vo
 * @className:      pageBean
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/29 15:57
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class pageBeanVo<T> {

//    当前页
//    private Integer currentPage;

//    页数
//    private Integer pageSize;

//     总页数
    private Integer totalPage;

//    本页的数据列表
    private List<T> recordList;



}