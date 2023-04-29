package com.leevan.mapper;

import com.leevan.pojo.userRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    @Select("select role_id from sys_user_role where identity = #{identity}")
    Integer selectRoleIdByIdentity(String identity);

    @Insert("insert into sys_user_role(user_id,role_id,identity) values(#{userId},#{roleId},#{identity})")
    Integer insert(userRole userRole);

    Integer delete(userRole userRole);

    @Select("select * from sys_user_role")
    List<userRole> findAll();
}
