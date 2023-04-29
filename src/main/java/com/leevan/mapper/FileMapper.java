package com.leevan.mapper;

import com.leevan.pojo.Files;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    @Insert("insert into sys_file(name,size,url,type) values(#{name},#{size},#{url},#{type})")
   public Integer insert(Files files);

}
