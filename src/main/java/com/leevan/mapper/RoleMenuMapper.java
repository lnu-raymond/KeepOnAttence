package com.leevan.mapper;

import com.leevan.pojo.roleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoleMenuMapper {

    Integer insert(roleMenu roleMenu);

    Integer delete(Integer roleId);

    List<Integer> findRoleMenuByRoleId(Integer roleId);

}
