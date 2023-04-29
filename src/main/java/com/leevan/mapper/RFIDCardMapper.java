package com.leevan.mapper;

import com.leevan.pojo.rfidCard;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RFIDCardMapper {


//    新增RFIDCard
    @Insert("insert into sys_rfid_card(epc,bind_status) values(#{epc},#{bindStatus})")
    public Integer addNewRFIDCard(rfidCard rfidCard);

//    查询所有未绑定状态或已经绑定的RFIDCard
    @Select("select epc ,bind_status from sys_rfid_card where bind_status = #{bindStatus}")
    public List<rfidCard> selectUnbindROrBoundfidCard(Integer bindStatus);

//    根据epc卡号查询rfidCard信息
    @Select("select epc,bind_status from sys_rfid_card where epc = #{epc}")
    public rfidCard selectRfidByEpc(@Param("epc") String epc);

    @Update("update sys_rfid_card set bind_status =#{bindStatus} where epc=#{epc}")
    public Integer updateBindStatusByEpc(String epc,Integer bindStatus);
}
