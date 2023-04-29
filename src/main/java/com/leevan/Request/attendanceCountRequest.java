package com.leevan.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.Request
 * @className:      attendanceCountRequest
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/18 8:46
 * @version:    1.0
 */ 

@AllArgsConstructor
@NoArgsConstructor
@Data
public class attendanceCountRequest {

    private String epc;//RFID卡号

    private Integer userId;//考勤对象

    private String fromDate;//考勤开始日期

    private String toDate;//考勤截止日期

    private String today;//对今天进行考勤统计
}