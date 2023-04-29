package com.leevan.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.vo
 * @className:      countDaysbyMonthVo
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/21 19:04
 * @version:    1.0
 */ 

@AllArgsConstructor
@NoArgsConstructor
@Data
public class countDaysbyMonthVo {

    private String nickName;

    private String identity;

    private String epc;

    private String month;

    private String day;


}