package com.leevan.controller;

import com.leevan.mapper.procedureSubmitMapper;
import com.leevan.pojo.approvalSubmit;
import com.leevan.pojo.procedureSubmit;

import com.leevan.service.procedureService;
import com.leevan.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      procedureController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/4 19:38
 * @version:    1.0
 */
@RestController
@RequestMapping("/procedure")
public class procedureController {
    @Autowired
    private procedureService procedureService;

    @Autowired
    private procedureSubmitMapper procedureSubmitMapper;

    @PostMapping("/submit")
    public Result procedureSubmit(@RequestBody procedureSubmit procedureSubmit) throws ParseException {
//        提交后调用服务方法在主流表添加数据
//        根据审批人的数量添加审批流详细表记录
//        给第一个人发送消息,更改第一条记录状态
        return Result.ok(procedureService.createNewProcedure(procedureSubmit));
    }

    @RequestMapping("/getSubmitDetailList/{userId}")
    public Result getSubmitDetailList(@PathVariable Integer userId)
    {
//        根据userId查询procedureSubmit表
//
        return Result.ok(procedureService.getMyProcedureAndApprovalResult(userId));
    }





}