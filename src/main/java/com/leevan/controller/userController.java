package com.leevan.controller;

import com.leevan.Request.userQueryRequest;
import com.leevan.mapper.UserMapper;
import com.leevan.pojo.*;
import com.leevan.service.roleService;
import com.leevan.service.userService;
import com.leevan.vo.Result;
import com.leevan.vo.pageBeanVo;
import com.leevan.vo.userVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      userController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/24 15:19
 * @version:    1.0
 */ 
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService userService;

    @Autowired
    private roleService roleService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/all")
//    返回用户所有信息
    public Result list()
    {
        return Result.ok(userService.findAll());
    }


//    返回用户个人信息

    @RequestMapping("/self")
    public Result getPersonSelfInfo(@RequestParam("userId")Integer userId)
    {
        userQueryRequest userQueryRequest = new userQueryRequest();
        userQueryRequest.setId(userId);
        user user = userMapper.findUser(userQueryRequest);
        return Result.ok(user);

    }


    @PostMapping("/add")
    public Integer save(@RequestBody userQueryRequest userQueryRequest)
    {
        return userService.save(userQueryRequest);
    }

    @PostMapping("/update")
    public Integer update(@RequestBody userQueryRequest userQueryRequest)
    {
        return userService.save(userQueryRequest);
    }

    @PostMapping("/login")
    public Result login(@RequestBody userQueryRequest userQueryRequest, HttpServletResponse response)
    {

//        校验账号是否正确,正确就返回200的状态码

//        登录成功返回用户角色对应的menu或者权限接口
        ServiceRes serviceRes = userService.CheckUser(userQueryRequest);

        if(serviceRes.getJwt()!=null)response.addHeader("Authentication",serviceRes.getJwt());

        if(serviceRes.getCode().equals(ResultCodeEnum.SUCCESS.getCode()))
        {
            return Result.ok(serviceRes.getData());
        }
        if(serviceRes.getMsg().equals(ResultCodeEnum.ACCOUNT_ERROR.getCode()))
        {
            return Result.build(null, ResultCodeEnum.ACCOUNT_ERROR.getCode(), ResultCodeEnum.ACCOUNT_ERROR.getMessage());
        }else if (serviceRes.equals(ResultCodeEnum.PASSWORD_ERROR.getCode()))
        {
            return Result.build(null,serviceRes.getCode(),serviceRes.getMsg());
        }
        return Result.build(null,serviceRes.getCode(),serviceRes.getMsg());
    }

    @GetMapping("/page")
    public pageBeanVo page(
            @RequestParam(value = "currentPage",defaultValue="1") Integer currentPage,
            @RequestParam(value = "pageSize",defaultValue ="5") Integer pageSize)
    {
//        这里需要改造页面数据,默认currentPage或者pagNum为1,pageSize和currentPage前端提交上来
       return userService.getPage(currentPage,pageSize);
    }

    @PostMapping("/batchDel")
    public Result batchDel(@RequestBody List<Integer> userIdList)
    {
//        调用userService删除用户
        return Result.ok(userService.batchDel(userIdList));
    }

    @PostMapping("/Del")
    public Result Del(@RequestBody Integer userId)
    {
        return Result.ok(userService.del(userId));
    }



    @RequestMapping("/queryPageByCondition")
    public pageBeanVo queryPageByCondition( userQueryRequest userQueryRequest)
    {
        return userService.getPageByCondition(userQueryRequest);
    }

}