package com.leevan.service;

import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.attendance;
import com.leevan.pojo.user;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface attendanceService {

//考勤上班打卡接口
    public ServiceRes PunchInAndOut(String  epc, String currentTime,Integer orientation);

    public List<attendance> findAllRecordsByuserId(Integer userId);


    public List<user> findUserAttendanceRecord(String epc,String currentDate);

    public List<attendance> findUserAttendanceRecordByEpc(String epc);

    public Integer countDays(String epc);

//    根据选中日期内查询记录
    public List<user> findUserAttendanceRecordDuringSelectDate(String epc,String fromDate,String toDate) throws ParseException;


//    考勤下班打卡接口g currentDa
//    public ServiceRes PunchOut(Integer userId,String lastPunchInTime,String currentTime);

}
