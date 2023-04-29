package com.leevan.controller;

import com.google.gson.Gson;
import com.leevan.Request.userQueryRequest;
import com.leevan.Utils.ByteTransferUtil;
import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.rfidCard;
import com.leevan.pojo.user;
import com.leevan.service.impl.rfidCardServiceImpl;
import com.leevan.service.rfidCardService;
import com.leevan.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      readRfidController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/17 9:36
 * @version:    1.0
 */ 

@RestController
@RequestMapping("/rfid")
public class readRfidController {

    @Autowired
    private rfidCardService rfidCardService;
    @PostMapping("/readRfidCard")
    public Result readRfidCard(@RequestParam("epc") String epc) throws IOException {
//        @RequestParam("epc") String epc
        //    读取硬件传递过来的RFID卡号
//        ByteTransferUtil byteTransferUtil = new ByteTransferUtil();
//        String epc = byteTransferUtil.getBodyTxt(request);
//        Gson gson = new Gson();
//        Integer parseEpc = Integer.valueOf(epc);
        ServiceRes serviceRes = rfidCardService.addNewRfidCard(epc);
        return Result.build(null,serviceRes.getCode(),serviceRes.getMsg());
    }
//    获取所有未被绑定的RFID卡号

    @RequestMapping("/UnBoundCard")
    public Result getUnBoundRfidCard( )
    {
        List<rfidCard> UnBindRfidCardList = rfidCardService.getBindOrUnBindRfidCardList(rfidCardServiceImpl.UnBound);
        return Result.ok(UnBindRfidCardList);
    }

    @RequestMapping("/BoundCard")
    public Result getBoundRfidCard()
    {
        List<rfidCard> BoundRfidCardList = rfidCardService.getBindOrUnBindRfidCardList(rfidCardServiceImpl.Bound);
        return Result.ok(BoundRfidCardList);
    }

    @PostMapping("/distribut")
    public Result distributeEpcForUser(@RequestBody userQueryRequest user)
    {
//        传递user对象 包含epc卡号
        ServiceRes serviceRes = rfidCardService.distributeRfidCard(user);
        return Result.build(null,serviceRes.getCode(),serviceRes.getMsg());
    }


}