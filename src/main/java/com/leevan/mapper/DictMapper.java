package com.leevan.mapper;

import com.leevan.pojo.dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DictMapper {

    @Select("select * from sys_dict where type=#{type} ")
    public List<dict> allByType(String type);


}
