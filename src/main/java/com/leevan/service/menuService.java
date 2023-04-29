package com.leevan.service;

import com.leevan.mapper.MenuMapper;
import com.leevan.pojo.dict;
import com.leevan.pojo.menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface menuService {



//    返回所有包含子菜单的父级菜单

    public List<menu> findAllMenus(List<menu> menuList);

    public menu findMenuById(Integer id);

    public Integer save(menu menu);

    public List<dict> getIconsByType(String type);

    public void distributeMenu(Integer roleId,List<Integer> menuIdsList);

    List<Integer> getRoleMenuIds(Integer roleId);

    Integer remove(Integer menuId);


//




}
