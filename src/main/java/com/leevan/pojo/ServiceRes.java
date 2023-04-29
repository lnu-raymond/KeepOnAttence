package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      ServiceRes
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/28 21:04
 * @version:    1.0
 */ 
@Data
@NoArgsConstructor
public  class ServiceRes<T> {

    private Integer code;

    private String msg;

    private String jwt;

    private T data;

    public ServiceRes(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceRes(Integer code, String msg, String jwt) {
        this.code = code;
        this.msg = msg;
        this.jwt = jwt;
    }

    public ServiceRes(Integer code, String msg, String jwt,T data) {
        this.code = code;
        this.msg = msg;
        this.jwt = jwt;
        this.data = data;
    }


}