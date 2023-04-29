package com.leevan.service;

import com.leevan.Request.userQueryRequest;
import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.rfidCard;
import com.leevan.pojo.user;

import java.util.List;

public interface rfidCardService {

//    新增rfid卡
    public ServiceRes addNewRfidCard(String epc);


//    分配rfid卡
    public ServiceRes distributeRfidCard(userQueryRequest user);

//    返回绑定或未绑定卡集合
    public List<rfidCard> getBindOrUnBindRfidCardList(Integer bindStatus);
}
