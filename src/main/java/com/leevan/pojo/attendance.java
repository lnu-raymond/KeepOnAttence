package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      attendance
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/10 8:18
 * @version:    1.0
 */ 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class attendance {
    private Integer id;

    private Integer userId;

    private String Record;

    private Integer orientation;

    private String epc;

}