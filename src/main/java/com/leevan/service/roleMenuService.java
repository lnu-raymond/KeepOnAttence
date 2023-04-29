package com.leevan.service;

import com.leevan.pojo.menu;
import com.leevan.pojo.roleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



public interface roleMenuService {

    Integer insert(roleMenu roleMenu);

    Integer batchDelete(Integer roleId);

    List<Integer> findMenusByRoleID(Integer roleId);

    void setRoleMenu(Integer roleId,List<Integer> menusId);


}
