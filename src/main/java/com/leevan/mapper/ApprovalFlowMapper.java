package com.leevan.mapper;

import com.leevan.pojo.approvalFlow;
import org.apache.ibatis.annotations.*;

//    审批流主表
@Mapper
public interface ApprovalFlowMapper {


    @Insert("insert into sys_approval_flow(flow_no,title,bus_type,user_id,status) values(#{flowNo},#{title},#{busType},#{userId},#{status})")
    Integer insert(approvalFlow approvalFlow);

    @Select("select * from sys_approval_flow where flow_no = #{FlowNo}")
    approvalFlow selectByFlowNo(@Param("FlowNo") Long FlowNo);

    @Delete("delete from sys_approval_flow where flow_no = #{FlowNo}")
    Integer delete(@Param("FlowNo")Long FlowNo);

    @Update("UPDATE sys_approval_flow set status =#{status} where flow_no=#{flowNo}")
    Integer updateStatusByFlowNo(@Param("status") String status,@Param("flowNo") Integer flowNo);





}
