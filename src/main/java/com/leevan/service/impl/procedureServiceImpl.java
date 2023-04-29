package com.leevan.service.impl;

import com.leevan.Request.userQueryRequest;
import com.leevan.mapper.ApprovalFlowDetailMapper;
import com.leevan.mapper.ApprovalFlowMapper;
import com.leevan.mapper.UserMapper;
import com.leevan.mapper.procedureSubmitMapper;
import com.leevan.pojo.*;
import com.leevan.service.procedureService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service.impl
 * @className:      procedureServiceImpl
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/4 20:41
 * @version:    1.0
 */ 

@Service
public class procedureServiceImpl implements procedureService {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static String STATUS_DENY="驳回";
    public static String STATUS_AGREE="通过";
    public static String STATUS_WAIT_APPROVAL="待我审批";
    @Autowired
    private procedureSubmitMapper procedureSubmitMapper;

    @Autowired
    private ApprovalFlowDetailMapper approvalFlowDetailMapper;

    @Autowired
    private ApprovalFlowMapper approvalFlowMapper;

    @Autowired
    private UserMapper userMapper;

//    新建一个流程
    @Override
    public Boolean createNewProcedure(procedureSubmit procedureSubmit) throws ParseException {

        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        //
        procedureSubmit.setStatus("待审批");
        Integer flowNo = procedureSubmit.getFlowNo();
        Integer procedureSubmitOfUserId = procedureSubmit.getUserId();


//        获取审批人主键
        List<Integer> approvalUserId = procedureSubmit.getCheckedApproval();

        userQueryRequest userQueryRequest = new userQueryRequest();
        userQueryRequest.setId(procedureSubmitOfUserId);
        String nickName = userMapper.findUser(userQueryRequest).getNickName();

        procedureSubmit.setNickName(nickName);
        procedureSubmitMapper.addNewProcedureSubmit(procedureSubmit);

        approvalFlow approvalFlow = new approvalFlow();
        approvalFlow.setFlowNo(flowNo);
        approvalFlow.setCreateTime(format.parse(format.format(new Date())));
        approvalFlow.setStatus("待审批");//1代表待审批
        approvalFlow.setUserId(procedureSubmitOfUserId);
//        approvalFlow.setTitle(procedureSubmit.getTitle());
        approvalFlowMapper.insert(approvalFlow);


        for(Integer i=0;i<approvalUserId.size();i++)
        {
            approvalFlowDetail approvalFlowDetail = new approvalFlowDetail();
//            关联请假申请表流程号
            approvalFlowDetail.setFlowNo(flowNo);
//            流程详细表审批人
            approvalFlowDetail.setUserId(approvalUserId.get(i));

            approvalFlowDetail.setCreateTime(format.parse(format.format(new Date())));

            approvalFlowDetail.setOrder(i);
            approvalFlowDetail.setStatus(STATUS_WAIT_APPROVAL);

            approvalFlowDetailMapper.insert(approvalFlowDetail);
        }
        return true;
    }

    @Override
    public ServiceRes DealOneProcedure(approvalSubmit approvalSubmit) throws ParseException {

//        Boolean hasNextApproval=false;


        String approvalTime = format.format(new Date());
        approvalFlowDetail approvalFlowDetail = new approvalFlowDetail();
        String opinion = approvalSubmit.getOpinion();
        String comments = approvalSubmit.getComments();
        Integer flowNo = approvalSubmit.getFlowNo();
        Integer userId = approvalSubmit.getApprovalUserId();
//        Integer approvalId = approvalSubmit.getApprovalId();
        Boolean IsPass;
        if(opinion.equals("同意"))
        {   IsPass=true;
            approvalFlowDetail.setApprovalTime(approvalTime);
            approvalFlowDetail.setComments(comments);
            approvalFlowDetail.setOpinion(opinion);
            approvalFlowDetail.setFlowNo(flowNo);
            approvalFlowDetail.setStatus(STATUS_AGREE);
            approvalFlowDetail.setUserId(userId);
        }else
        {
            IsPass=false;
            approvalFlowDetail.setApprovalTime(approvalTime);
            approvalFlowDetail.setComments(comments);
            approvalFlowDetail.setOpinion(opinion);
            approvalFlowDetail.setFlowNo(flowNo);
            approvalFlowDetail.setUserId(userId);
            approvalFlowDetail.setStatus(STATUS_DENY);
        }

        approvalFlowDetailMapper.updateByFlowNoAnduserId(approvalFlowDetail);

//        判断是否还有下一个审批需要处理,状态为待我审批
//        如果还有待审批的记录,对审批主流表不做操作

        String  status= "待我审批";
        List<approvalFlowDetail>  hasNextApprovalFlowDetail= approvalFlowDetailMapper.hasNextApprovalFlowDetailByStatusAndFlowNo(flowNo, status);

        if(IsPass)
        {
            List<com.leevan.pojo.approvalFlowDetail> hasDenyApprovalFlowDetail = approvalFlowDetailMapper.hasNextApprovalFlowDetailByStatusAndFlowNo(flowNo, STATUS_DENY);

            if(hasNextApprovalFlowDetail.size()>0&&hasNextApprovalFlowDetail!=null) {

                return new ServiceRes(0,"尚有审批人未审批完申请~");
//            hasNextApproval = true;
//                approvalFlowDetail NextapprovalFlowDetail = hasNextApprovalFlowDetail.get(0);
//                NextapprovalFlowDetail.setStatus("2");
//                approvalFlowDetailMapper.updateAllByFlowNo(NextapprovalFlowDetail);
            }

            if(hasDenyApprovalFlowDetail==null)
            {

                approvalFlowMapper.updateStatusByFlowNo(STATUS_AGREE,flowNo);
                procedureSubmitMapper.updateStatus(flowNo,STATUS_AGREE);
                return new ServiceRes(1,"审批通过啦~~");
            }else
            {
                approvalFlowMapper.updateStatusByFlowNo(STATUS_DENY,flowNo);
                procedureSubmitMapper.updateStatus(flowNo,STATUS_DENY);
                return new ServiceRes (-1,"有未审批通过的记录因此审批不通过！！");
            }
        }else
        {
            approvalFlowMapper.updateStatusByFlowNo(STATUS_DENY,flowNo);
            procedureSubmitMapper.updateStatus(flowNo,STATUS_DENY);
            return new ServiceRes(-1,"审批给驳回了 不好意思~~");
        }

    }

    @Override
    public Integer getUserOfProcedureWhoCreateBy(Integer flowNo) {
        return null;
    }

    @Override
    public List<approvalFlowDetail> getApprovalFlowDetail(Integer flowNo) {
        return null;
    }

    @Override
    public procedureSubmit getMySubmitAllInforByFlowNo(Integer FlowNo) {
        return null;
    }

    @Override
    public List<procedureSubmit> getMyProcedureAndApprovalResult(Integer userId) {
        List<procedureSubmit> procedureSubmits = approvalFlowDetailMapper.selectSubmitAndApprovalResult(userId);
        return procedureSubmits;
    }

    @Override
    public boolean procedureSubmit(procedureSubmit procedureSubmit) {
        return false;
    }

    @Override
    public List<Integer> getFlowNo(Integer userId, String status) {
        return approvalFlowDetailMapper.getAFlowNo(userId,status);
    }

    @Override
    public List<procedureSubmit> getWaitAndApprovalList(List<Integer> flowNos) {
        ArrayList<procedureSubmit> waitAndApproval = new ArrayList<>();
        for (Integer flowNo:flowNos) {
           waitAndApproval.add( procedureSubmitMapper.getProcedureSubmit(flowNo));
        }
        return waitAndApproval;
    }

    @Override
    public List<procedureSubmit> returnWaitAndApproval(Integer userId, String status) {
//        调用获取流程号方法获取
//        根据流程号返回所有待审批的申请提交表
        List<Integer> flowNos = getFlowNo(userId, status);
        List<procedureSubmit> waitAndApprovalList = getWaitAndApprovalList(flowNos);
        return waitAndApprovalList;
    }


}