package com.leevan.controller;

import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.approvalSubmit;
import com.leevan.service.procedureService;
import com.leevan.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      approvalController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/4 21:38
 * @version:    1.0
 */ 
@RestController
@RequestMapping("/approval")
public class approvalController {

    @Autowired
    private procedureService procedureService;


    @PostMapping("/dealOneApproval")
    public Result approvalSubmit(@RequestBody approvalSubmit approvalSubmit) throws ParseException {
//        根据表单提交录入审批信息及处理一个审批详情
        ServiceRes serviceRes = procedureService.DealOneProcedure(approvalSubmit);
        return Result.build(null,serviceRes.getCode(),serviceRes.getMsg());
    }

    @GetMapping("/waitAndApproval/{userId}")
    public Result waitAndApproval(@PathVariable("userId") Integer userId)
    {
//        根据userId查询flow_no
//        根据flow_no查询procedureSubmit
//        找出待我审批审批记录表
        String status = "待我审批";
        return Result.ok(procedureService.returnWaitAndApproval(userId,status));

    }

}