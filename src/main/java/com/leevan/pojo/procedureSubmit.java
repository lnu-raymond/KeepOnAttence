package com.leevan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.pojo
 * @className:      approvalSubmit
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/4 9:36
 * @version:    1.0
 */ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class procedureSubmit {

    private Integer flowNo;//流程编号

    private String nickName;//申请人昵称

    private String type;//申请类型

    private Integer userId;//创建人id

    private String startDate;;//请假开始日期

    private String startTime;//请假开始时间

    private String endDate;//开始时间

    private String endTime;//结束时间

//    private Integer status; //状态

    private String showFileName;//上传的文件名

    private String reason;//请假的原因

    private Integer timeHours;

    private String status;
    //聚合审批人
    private List<Integer> checkedApproval;

    private List<approvalFlowDetail> approvalFlowDetailList;





}