package com.leevan.mapper;


import com.leevan.pojo.approvalFlowDetail;
import com.leevan.pojo.procedureSubmit;
import org.apache.ibatis.annotations.*;

import java.util.List;

//操作审批明细表
@Mapper
public interface ApprovalFlowDetailMapper {

//    根据审批状态查询明细表
    @Select("select * from sys_approval_flow_detail where status =#{status}")
    List<approvalFlowDetail> seleteByStatus(String status);

//    根据flow_no查询明细表
    @Select("select * from sys_approval_flow_detail where flow_no = #{flowNo}")
    List<approvalFlowDetail> selectByAppNo(Long flowNo);

//    新增审批明细表
    @Insert("insert into sys_approval_flow_detail(flow_no,user_id,approval_time,opinion,status,comments) " +
            "value(#{flowNo},#{userId},#{approvalTime},#{opinion},#{status},#{comments})")
    Integer insert(approvalFlowDetail approvalFlowDetail);

//    根据flow_no修改修改审批明细表status
    @Update("update sys_approval_flow_detail set comments = #{comments},status = #{status},opinion=#{opinion},approval_time=#{approvalTime} where flow_no = #{flowNo} and user_id=#{userId}")
    public Integer  updateByFlowNoAnduserId(approvalFlowDetail approvalFlowDetail);

//    根据审批意见更新流程详细表
    public Integer updateAllByFlowNo(approvalFlowDetail approvalFlowDetail);

    @Select("select * from sys_approval_flow_detail where flow_no = #{flowNo} and status = #{status}")
//    根据flowno和status查询是否还有待审批的流程
    public List<approvalFlowDetail> hasNextApprovalFlowDetailByStatusAndFlowNo(Integer flowNo,String status);

    @Select("select * from sys_approval_flow_detail where order=#{order} and flowNo=#{flowNo}")
    public approvalFlowDetail selectByOrderAndFlowNo(Integer order,Integer flowNo);

    @Select("select flow_no from sys_approval_flow_detail where user_id = #{userId} and status=#{status}")
    public List<Integer> getAFlowNo(@Param("userId") Integer userId,@Param("status") String status);

    public List<procedureSubmit> selectSubmitAndApprovalResult(Integer userId);
}
