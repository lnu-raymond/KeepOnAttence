package com.leevan.service.impl;

import com.leevan.mapper.DictMapper;
import com.leevan.mapper.MenuMapper;
import com.leevan.mapper.RoleMenuMapper;
import com.leevan.pojo.dict;
import com.leevan.pojo.menu;
import com.leevan.pojo.roleMenu;
import com.leevan.service.menuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service.impl
 * @className:      menuServiceImpl
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/30 11:40
 * @version:    1.0
 */ 
@Service
public class menuServiceImpl implements menuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private DictMapper dictMapper;

    private List<menu> menuList;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<menu> findAllMenus(List<menu> menuslist) {
        if(menuslist!=null)
        {
            menuList=menuslist;
        }else
        {
            menuList=menuMapper.findAll();
//            return menuList;
        }

        List<menu> parentNode = new ArrayList<>();
//        List<menu> collect = menuStream.collect(Collectors.toList());
        for (menu menu: menuList) {
            if(menu==null) {
                break;
            }
                else if((menu.getPid() == 0 ))
                {
                    parentNode.add(menu);
                }

        }
//        Stream<menu> menuStream = menuList.stream().filter(menu -> menu.getPid() == 0);
//        List<menu> parentNode = menuList.stream().filter(menu -> menu.getPid() == 0).collect(Collectors.toList());
//  找出包含子菜单的节点,赋值给父级菜单的children

        for(menu menu :parentNode)
        {
            menu.setChildren(getChildren(menu.getId(),menuList));
        }
        return parentNode;
    }



    @Override
    public menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public Integer save(menu menu)
    {
//        判断menu.id是否为null
        if(menu.getId()==null&&menu.getPid()==null)
        {
           return menuMapper.save(menu);
        }else if (menu.getId()==null &&menu.getPid()!=null)
        {
            return menuMapper.saveSubMenu(menu);
        }
            else
        {
         return menuMapper.update(menu);
        }

    }

    @Override
    public List<dict> getIconsByType(String type) {

        return dictMapper.allByType(type);
    }

    @Override
    public void distributeMenu(Integer roleId, List<Integer> menusIdList) {

         roleMenuMapper.delete(roleId);
        for (Integer menuItem: menusIdList) {
            roleMenu roleMenu = new roleMenu(roleId, menuItem);
            roleMenuMapper.insert(roleMenu);
        }


    }

    @Override
    public List<Integer> getRoleMenuIds(Integer roleId) {

        return roleMenuMapper.findRoleMenuByRoleId(roleId);
    }

    @Override
    public Integer remove(Integer menuid) {


        return menuMapper.deleteMenu(menuid);
    }

    public List<menu> getChildren(Integer Id,List<menu> menusList)
    {
        List<menu> childrenList = new ArrayList<>();
        childrenList = menusList.stream().filter(menu -> Id.equals(menu.getPid())).collect(Collectors.toList());

        for (menu menu: childrenList ) {
            menu.setChildren(getChildren(menu.getId(),menusList));
        }
        return childrenList;
    }

}