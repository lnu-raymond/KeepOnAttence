package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      dict
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/13 9:32
 * @version:    1.0
 */ 

@Data
@AllArgsConstructor
@NoArgsConstructor
public class dict {
    private String name;

    private String value;

    private String type;
}