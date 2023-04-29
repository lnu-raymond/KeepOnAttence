package com.leevan.mapper;


import com.leevan.pojo.role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    public List<role> findAll();

    public Integer save(role role);

    public Integer update(role role);

    public role findRoleById(Integer id);

    Integer delRole(role role);

    @Select("select count(*) from sys_role")
    public Integer countPage();

    List<role> selectAllWithPage(Integer pageNum, Integer pageSize);
}
