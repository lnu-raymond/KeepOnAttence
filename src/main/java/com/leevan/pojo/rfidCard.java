package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      rfidCard
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/17 10:02
 * @version:    1.0
 */ 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class rfidCard {

    private String epc;//电子标签卡号

    private Integer bindStatus;//标签卡状态 0:未绑定 1已绑定


}