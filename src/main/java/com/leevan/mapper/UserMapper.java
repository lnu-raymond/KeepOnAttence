package com.leevan.mapper;
import com.leevan.Request.userQueryRequest;
import com.leevan.pojo.user;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper{

    @Select("select * from sys_user")
    List<user> findAll();

    @Insert("insert into sys_user(user_name,identity,nick_name,password,email,phonenumber,sex,epc) VALUES(#{userName},#{identity},#{nickName},#{passWord},#{email},#{phonenumber},#{sex},#{epc})")
    Integer save(userQueryRequest userQueryRequest);

    Integer update(userQueryRequest userQueryRequest);

    user findUser(userQueryRequest userQueryRequest);

    @Select("select count(*) from sys_user")
    Integer countPages();

    Integer countPagesByCondition(userQueryRequest userQueryRequest);

    List<user> findAllWithPageAndCondition(userQueryRequest queryRequest);

    List<user> findAllWithPage(@Param("pageNum") Integer pageNum ,
                               @Param("pageSize")Integer pageSize);

    @Delete("delete from sys_user where id = #{userId}")
    Integer delete(Integer userId);






}
