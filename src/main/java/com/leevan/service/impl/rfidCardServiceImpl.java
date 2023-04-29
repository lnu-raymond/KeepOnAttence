package com.leevan.service.impl;

import com.leevan.Request.userQueryRequest;
import com.leevan.mapper.RFIDCardMapper;
import com.leevan.mapper.UserMapper;
import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.rfidCard;
import com.leevan.pojo.user;
import com.leevan.service.rfidCardService;
import com.leevan.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service
 * @className:      rfidCardServiceImpl
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/17 10:24
 * @version:    1.0
 */ 
@Service
public class rfidCardServiceImpl implements rfidCardService {

    public static Integer Bound = 1;//已绑定
    public static Integer UnBound  = 0;//未绑定

    @Autowired
    private RFIDCardMapper rfidCardMapper;

    @Autowired
    private userService userService;
    @Override
    public ServiceRes addNewRfidCard(String epc) {
        if(epc == null)
        {
            return new ServiceRes(-1,"卡号为空 新增失败~~");
        }
        rfidCard rfidCard = rfidCardMapper.selectRfidByEpc(epc);

        if(rfidCard!=null)
        {
            return new ServiceRes(-1,"不能新增重复的卡");
        }
        else
        {
            rfidCard cardInfo = new rfidCard();
            cardInfo.setEpc(epc);
            cardInfo.setBindStatus(UnBound);
            Integer bindResult = rfidCardMapper.addNewRFIDCard(cardInfo);
            return new ServiceRes(1,"新增了一条未绑定RFID卡到系统");
        }
    }

    @Override
    public ServiceRes distributeRfidCard(userQueryRequest userQueryRequest) {

        Integer save = userService.save(userQueryRequest);
        if(save!=null)
        {
            rfidCardMapper.updateBindStatusByEpc(userQueryRequest.getEpc(),rfidCardServiceImpl.Bound);
            return new ServiceRes(1,"分配Epc成功~");
        }else
        {
            return new ServiceRes(-1,"分配失败了~");
        }

    }

    @Override
    public List<rfidCard> getBindOrUnBindRfidCardList(Integer bindStatus) {
        return rfidCardMapper.selectUnbindROrBoundfidCard(bindStatus);
    }
}