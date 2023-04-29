package com.leevan.vo;

import com.leevan.pojo.attendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.vo
 * @className:      attenceRecordVo
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/17 15:38
 * @version:    1.0
 */ 

@Data
@AllArgsConstructor
@NoArgsConstructor
public class attendanceRecordVo {

    private String nickName;

    private String epc;

    private String record;

    private String identity;

    private Integer orientation;


}