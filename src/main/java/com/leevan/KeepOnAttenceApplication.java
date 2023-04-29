package com.leevan;

import com.leevan.mapper.UserMapper;
import com.leevan.pojo.user;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan
 * @className:      KeepOnAttenceApplication
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/15 16:10
 * @version:    1.0
 */

@SpringBootApplication
public class KeepOnAttenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeepOnAttenceApplication.class,args);
//        System.out.println();
    }




}