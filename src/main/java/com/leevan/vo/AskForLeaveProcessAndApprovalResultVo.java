package com.leevan.vo;

import com.leevan.pojo.approvalFlowDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.vo
 * @className:      AskForLeaveProcessAndApprovalResultVo
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/16 15:36
 * @version:    1.0
 */ 


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AskForLeaveProcessAndApprovalResultVo {
    private String nickName;//申请对象

    private String status;//申请表状态

    private String reason;//申请的理由

    private String type;//请假类型

    private String startDate;;//请假开始日期

    private String startTime;//请假开始时间

    private String endDate;//开始时间

    private String endTime;//结束时间

    private String approvalTime;//审批的时间

    private String opinion;//审批的意见

    private String comments;//审批评论和意见

//    private String Approver;//审批对象

    private List<approvalFlowDetail> approvalFlowDetailList;

}