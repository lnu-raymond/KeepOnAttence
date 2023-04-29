package com.leevan.mapper;

import com.leevan.pojo.attendance;
import com.leevan.pojo.user;
import com.leevan.vo.countDaysbyMonthVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


@Mapper
public interface AttendanceMapper
{

// 新增考勤记录表记录
 @Insert("insert into sys_attendance(record,orientation,epc) values(#{Record},#{orientation},#{epc})")
 public Integer punchInAndOut(attendance attendance);


// 根据时间日期查询考勤记录
 @Select("select * from sys_attendance where record like '%${attendance.Record}%' and user_id =#{attendance.userId}")
 public List<attendance> selectRecordsByDateAndUserId(attendance attendance);

// 根据userId去查询考勤记录
 @Select("select * from sys_attendance where user_id = #{userId}")
 public List<attendance> selectByUserId(Integer userId);

 public List<user> selectUserAttendanceRecord(String  epc,String record);

// 根据日期选择记录
 public List<user> selectRangeDateParentRecord(String epc,String fromDate,String toDate);



 @Select("select * from sys_attendance where epc=#{epc}")
public List<attendance> SelectByEpc(@Param("epc") String epc);

 public Integer countDays(@Param("epc") String epc);



}
