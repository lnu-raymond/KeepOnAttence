package com.leevan;

import com.leevan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan
 * @className:      MPTest
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/15 17:12
 * @version:    1.0
 */ 
@SpringBootTest

public class MPTest {
    @Autowired
    private UserMapper UserMapper;

}