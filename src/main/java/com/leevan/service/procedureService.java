package com.leevan.service;

import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.approvalFlowDetail;
import com.leevan.pojo.approvalSubmit;
import com.leevan.pojo.procedureSubmit;

import java.text.ParseException;
import java.util.List;

public interface procedureService {

//    新建一个流程
    public Boolean createNewProcedure(procedureSubmit procedureSubmit) throws ParseException;


//    审批一个流程
    public ServiceRes DealOneProcedure(approvalSubmit approvalSubmit) throws ParseException;

//    获取流程的创建者
    public Integer getUserOfProcedureWhoCreateBy(Integer flowNo);

//    根据流程号获取详细流程
    public List<approvalFlowDetail> getApprovalFlowDetail(Integer flowNo);
//    获取我的一个提交的全部信息
    public procedureSubmit getMySubmitAllInforByFlowNo(Integer FlowNo);

//    获取申请信息和审批进度
    public List<procedureSubmit> getMyProcedureAndApprovalResult(Integer userId);

//    流程提交
    public boolean procedureSubmit(procedureSubmit procedureSubmit);

    public List<Integer> getFlowNo(Integer userId,String status);

//    通过流程号返回所有待审批的记录
    public List<procedureSubmit> getWaitAndApprovalList(List<Integer> flowNo);

//    返回所有审批状态为待我审批申请流程表
    public List<procedureSubmit> returnWaitAndApproval(Integer userId,String status);


}
