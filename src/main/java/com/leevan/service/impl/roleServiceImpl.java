package com.leevan.service.impl;

import com.leevan.mapper.RoleMapper;
import com.leevan.pojo.role;
import com.leevan.pojo.user;
import com.leevan.service.roleService;
import com.leevan.vo.pageBeanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service.impl
 * @className:      roleServiceImpl
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/29 20:14
 * @version:    1.0
 */


@Service
public class roleServiceImpl implements roleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Integer save(role role) {
//        新增和修改都在这个方法内部判断
//        如果role.id不存在就新增,存在就更新
        if(role.getId()==null)
        {
            return roleMapper.save(role);
        }else {
            return roleMapper.update(role);
        }

    }

    @Override
    public role findUserById(Integer id) {
        return roleMapper.findRoleById(id);
    }

    @Override
    public pageBeanVo getPage(Integer pageNum, Integer pageSize) {
        pageBeanVo pageBeanVo = new pageBeanVo();
        pageBeanVo.setTotalPage(roleMapper.countPage());
        List<role> roles = roleMapper.selectAllWithPage(pageNum, pageSize);
        pageBeanVo.setRecordList(roles);
        return pageBeanVo;
    }

    @Override
    public Integer remove(Integer id) {
//        根据id查询role
        role roleOfDao = roleMapper.findRoleById(id);
        return roleMapper.delRole(roleOfDao);

    }


}