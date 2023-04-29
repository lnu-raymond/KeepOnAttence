package com.leevan.mapper;

import com.leevan.pojo.menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    public List<menu> findAll();

    public Integer save(menu menu);

    public Integer update(menu menu);

    public menu findMenuById(Integer Id);

    @Insert("insert into sys_menu(menu_name,path,icon,pid) values(#{menuName},#{path},#{icon},#{pid})")
    public Integer saveSubMenu(menu menu);


    @Delete("delete from sys_menu where id=#{id} ")
    Integer deleteMenu(Integer id);
}
