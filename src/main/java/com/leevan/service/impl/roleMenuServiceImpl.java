package com.leevan.service.impl;

import com.leevan.mapper.RoleMenuMapper;
import com.leevan.pojo.menu;
import com.leevan.pojo.roleMenu;
import com.leevan.service.roleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service.impl
 * @className:      roleMenuServiceImpl
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/31 12:34
 * @version:    1.0
 */ 
@Service
public class roleMenuServiceImpl implements roleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Override
    public Integer insert(roleMenu roleMenu) {
        return roleMenuMapper.insert(roleMenu);
    }

    @Override
    public Integer batchDelete(Integer roleId) {
            return roleMenuMapper.delete(roleId);
    }

    @Override
    public List<Integer> findMenusByRoleID(Integer roleId) {
        return roleMenuMapper.findRoleMenuByRoleId(roleId);
    }

    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menusId)
    {
//        充值角色对应的menuId
        roleMenuMapper.delete(roleId);
//        多次添加menu
        for(Integer menuId:menusId)
        {
            roleMenuMapper.insert(new roleMenu(roleId,menuId));
        }

    }


}