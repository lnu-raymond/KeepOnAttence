package com.leevan.service;

import com.leevan.pojo.role;
import com.leevan.vo.pageBeanVo;

import java.util.List;

public interface roleService {

    public List<role> findAll();

    //    实现user表更新和更新
    public Integer save(role role);

    public role findUserById(Integer id);

    public pageBeanVo getPage(Integer pageNum,Integer pageSize);

    Integer remove(Integer id);
}
