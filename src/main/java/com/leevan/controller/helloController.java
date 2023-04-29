package com.leevan.controller;

import com.leevan.vo.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      helloController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/10 10:33
 * @version:    1.0
 */ 

//@RequestMapping("/attendance")
@RestController
public class helloController {

    @RequestMapping("/test")
    public String testGet()
    {
        return "ok success";
    }

//    @PostMapping("/signin")
//    public Result signIn(@RequestParam("userId") String userId) {
//
//        return service.signIn(userId);
//    }


}