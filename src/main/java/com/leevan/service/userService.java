package com.leevan.service;

import com.leevan.Request.userQueryRequest;
import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.user;
import com.leevan.vo.pageBeanVo;
import com.leevan.vo.userVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service
 * @className:      userService
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/24 16:24
 * @version:    1.0
 */ 
@Service
public interface userService {

//    实现user表更新和更新
    public Integer save(userQueryRequest userQueryRequest);

//  返回所有用户信息
    public List<userVo> findAll();
//    校验用户登录
    public ServiceRes CheckUser(userQueryRequest userQueryDto);


    public userVo findUserById(userQueryRequest queryRequest);

    public pageBeanVo getPage(Integer pageNum, Integer pageSize);

    public Integer del(Integer userId);

    public Integer batchDel(List<Integer> userIdList);

    pageBeanVo getPageByCondition(userQueryRequest userQueryRequest);
}