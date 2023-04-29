package com.leevan.service.impl;

import com.leevan.mapper.UserRoleMapper;
import com.leevan.pojo.userRole;
import com.leevan.service.userRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service.impl
 * @className:      userRoleServiceImpl
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/1 9:44
 * @version:    1.0
 */ 
@Service
public class userRoleServiceImpl implements userRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public Integer selectRoleIdByIdentity(String identity) {
        return userRoleMapper.selectRoleIdByIdentity(identity);
    }

    @Override
    public Integer insert(userRole userRole) {
        return userRoleMapper.insert(userRole);
    }

    @Override
    public Integer delete(userRole userRole) {
        return userRoleMapper.delete(userRole);
    }

    @Override
    public List<userRole> findAll() {
        return userRoleMapper.findAll();
    }
}