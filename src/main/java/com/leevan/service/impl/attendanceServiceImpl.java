package com.leevan.service.impl;

import com.leevan.Utils.DateUtils;
import com.leevan.mapper.AttendanceMapper;
import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.attendance;
import com.leevan.pojo.user;
import com.leevan.service.attendanceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service.impl
 * @className:      attendanceServiceImpl
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/11 13:09
 * @version:    1.0
 */ 
@Service
public class attendanceServiceImpl implements attendanceService {

//    private static String waiting="等待签退";
//    private static String finished="完成";

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public ServiceRes PunchInAndOut(String epc, String currentTime,Integer orientation) {

        attendance attendance = new attendance();

//        attendance.setUserId(userId);
        attendance.setEpc(epc);
        attendance.setRecord(currentTime);
        attendance.setOrientation(orientation);

        Integer Result = attendanceMapper.punchInAndOut(attendance);

        if(Result>0&&Result!=null)
        {
            return new  ServiceRes(1,"打卡成功 上班愉快哦");
        }
        else
        {
            return new ServiceRes(-1,"打卡失败 不好意思啊");
        }

    }

    @Override
    public List<attendance> findAllRecordsByuserId(Integer userId) {

        return attendanceMapper.selectByUserId(userId);
    }

    @Override
    public List<user> findUserAttendanceRecord(String epc,String record) {
        List<user> users = attendanceMapper.selectUserAttendanceRecord(epc,record);
//        List<user> users=attendanceMapper.SelectByEpc(epc);
        return users;
    }

    @Override
    public List<attendance> findUserAttendanceRecordByEpc(String epc) {
        List<attendance> attendanceList = attendanceMapper.SelectByEpc(epc);

        return attendanceList;
    }

    @Override
    public Integer countDays(String epc) {

        return attendanceMapper.countDays(epc);
    }

    @Override
    public List<user> findUserAttendanceRecordDuringSelectDate(String epc, String fromDate, String toDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(toDate);
        String afterDay = DateUtils.getAfterDay(parse);
        return attendanceMapper.selectRangeDateParentRecord(epc,fromDate,afterDay);
    }


//    @Override
//    public ServiceRes PunchOut(Integer userId, String lastPunchInTime,String currentTime) {
//
//        ServiceRes serviceRes = new ServiceRes();
////        去数据库查询上次打卡时间
//        attendance attendance = attendanceMapper.selectLastPunchInRecord(userId, lastPunchInTime);
//
//        if(attendance==null)
//        {
//            serviceRes.setCode(-1);
//            serviceRes.setMsg("记录为空 上次打卡记录异常或者未打卡");
//        }
//
////        设置记录表签退时间,修改这次考勤记录的状态
//        attendance.setEndRecord(currentTime);
//        attendance.setStatus(finished);
////      计算两次打卡间距差值获取实际出勤时间
////        带小数点的持续时间
//        Float span = DateUtils.dateSubtractionTransferHours(attendance.getStartRecord(), currentTime);
////        attendance.setSpan(span);
//        Integer result = attendanceMapper.punchOut(userId, attendance);
//        if(result!=null && result >0)
//        {
//            serviceRes.setCode(1);
//            serviceRes.setMsg("考勤成功,记得带齐随身物品下班愉快");
//        }
//
//        return serviceRes;
//    }
}