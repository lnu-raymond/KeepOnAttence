package com.leevan.controller;

import com.leevan.vo.Result;
import com.leevan.pojo.menu;
import com.leevan.service.menuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      menuController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/29 21:14
 * @version:    1.0
 */ 
@RestController
@RequestMapping("/menu")
public class menuController {
    @Autowired
    private menuService menuService;

    @GetMapping("/all")
    public Result findAll()
    {
        return Result.ok(menuService.findAllMenus(null));
    }

    @GetMapping("/edit/{Id}")
    public Result edit(@PathVariable Integer Id)
    {
          return Result.ok();
    }

    @PostMapping("/addSubMenu/{pid}")
    public Result addSubMenu(@PathVariable Integer pid,@RequestBody menu menu)
    {
        menu.setPid(pid);
        return Result.ok(menuService.save(menu));
    }

    @GetMapping("/icons")
    public Result getIcons()
    {
        String type="icon";
        return Result.ok(menuService.getIconsByType(type));
    }

    @PostMapping("/deleteMenu")
    public Result deleteMenu(@RequestBody Integer id)
    {
        return Result.ok(menuService.remove(id));
    }


    @PostMapping("/distruibuteMenu/{roleId}")

    public Result distributeMenu(@PathVariable("roleId") Integer roleId,@RequestBody List<Integer> checkedmMenuIds)
    {
        menuService.distributeMenu(roleId,checkedmMenuIds);

        return Result.ok();
    }

//    分配子菜单和新增父级菜单
    @PostMapping("/add")
    public Result addMenu(@RequestBody menu menu)
    {
        return Result.ok(menuService.save(menu));
    }


    @RequestMapping("/{id}")
    public Result findOne(@PathVariable Integer id)
    {
        return Result.ok(menuService.findMenuById(id));
    }

    @RequestMapping("/getRoleMenu")

        public List<Integer> getRoleMenuIds(Integer roleId)
    {
        return menuService.getRoleMenuIds(roleId);
    }




}