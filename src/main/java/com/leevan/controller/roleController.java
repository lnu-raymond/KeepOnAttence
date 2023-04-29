package com.leevan.controller;

import com.leevan.vo.Result;
import com.leevan.pojo.role;
import com.leevan.service.menuService;
import com.leevan.service.roleMenuService;
import com.leevan.service.roleService;
import com.leevan.service.userRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      roleController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/29 20:37
 * @version:    1.0
 */

@RestController
@RequestMapping("/role")
public class roleController {
    @Autowired
    private roleService roleService;

    @Autowired
    private menuService menuService;

    @Autowired
    private roleMenuService roleMenuService;

    @Autowired
    private userRoleService userRoleService;

    @RequestMapping("/all")
    public Result list()
    {
        return Result.ok(roleService.findAll());
    }

    @RequestMapping("/page")
    public Result getPage(@RequestParam(value = "currentPage") Integer currentPage,@RequestParam(value = "pageSize")Integer pageSize)
    {
        Integer pageNum= (currentPage-1)*pageSize;
        return Result.ok(roleService.getPage( pageNum,pageSize));
    };

    @PostMapping("/add")
    public Integer addRole(role role)
    {
        return roleService.save(role);
    }

    @PostMapping("/update")
    public Integer updateRole(role role)
    {
        return roleService.save(role);
    }

    @RequestMapping("/del/{id}")
    public Integer delRole(@PathVariable Integer id)
    {
      return roleService.remove(id);
    }

//    分配角色菜单
    @PostMapping("/distribute/{roleId}")
//    传角色的id，给当前角色分配菜单
    public Result distributeMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menusId)
    {
        roleMenuService.setRoleMenu(roleId,menusId);
        return Result.ok( );
    }
//    返回角色菜单
    @RequestMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId)
    {
        return Result.ok(roleMenuService.findMenusByRoleID(roleId));
    }

    @RequestMapping("/userRole")

    public Result getUserRole()
    {
        return Result.ok(userRoleService.findAll());
    }









}