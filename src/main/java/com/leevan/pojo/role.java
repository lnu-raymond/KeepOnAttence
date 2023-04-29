package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      role
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/29 9:56
 * @version:    1.0
 */ 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class role {
    private Integer id;

    private String name;

    private String description;

    private String status;

}