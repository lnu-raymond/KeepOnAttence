package com.leevan.mapper;

import com.leevan.pojo.procedureSubmit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface procedureSubmitMapper {

//    新增请假申请表记录
    @Insert("insert into sys_procedure_submit(flow_no,user_id,reason,start_time,end_time,start_date,end_date,time_hours,type,status,show_file_name,nick_name) values(#{flowNo},#{userId},#{reason},#{startTime},#{endTime},#{startDate},#{endDate},#{timeHours},#{type},#{status},#{showFileName},#{nickName})")
    public Integer addNewProcedureSubmit(procedureSubmit procedureSubmit);

//    查看请假申请表记录
    @Select("select * from sys_procedure_submit where flow_no=#{FlowNo}")
    public procedureSubmit getProcedureSubmit(Integer FlowNo);

//根据userid获取procedureSubmit集合
    @Select("select * from sys_procedure_submit where user_id=#{userId}")
    public List<procedureSubmit> getProcedureSubmitByuserId(Integer userId);

    @Update("update sys_procedure_submit set status=#{status} where flow_no=#{flowNo}")
    public Integer updateStatus(Integer flowNo,String status);
}
