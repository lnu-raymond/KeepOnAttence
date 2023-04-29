package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      Files
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/14 18:53
 * @version:    1.0
 */ 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Files {
    private String name;

    private String type;

    private Integer size;

    private String url;

}