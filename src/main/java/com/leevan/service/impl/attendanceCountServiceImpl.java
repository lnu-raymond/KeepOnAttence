package com.leevan.service.impl;

import com.leevan.Utils.DateUtils;
import com.leevan.pojo.attendance;
import com.leevan.pojo.user;
import com.leevan.service.attendanceCountService;
import com.leevan.service.attendanceService;
import com.leevan.vo.attendanceCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.service.impl
 * @className:      attendanceCountServiceImpl
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/18 14:24
 * @version:    1.0
 */ 
@Service
public class attendanceCountServiceImpl implements attendanceCountService {

    @Autowired
    private attendanceService attendanceService;

//        查询当天考勤时间
    @Override
    public attendanceCountVo attendanceCountByDay(String epc, String currentDate) throws ParseException {

        List<user> userAttendanceRecord = attendanceService.findUserAttendanceRecord(epc, currentDate);
        List<attendance> attendanceList = userAttendanceRecord.get(0).getAttendanceList();
//        对数据进行排序
//        attendanceList.sort();

        Float result=getAttendanceSpan(attendanceList);
        attendanceCountVo attendanceCountVo = new attendanceCountVo();
        attendanceCountVo.setSpan(result);
        attendanceCountVo.setNickName(userAttendanceRecord.get(0).getNickName());
        return attendanceCountVo;
    }


//    给定日期查询考勤时间
    @Override
    public attendanceCountVo attendanceCountByDate(String epc, String fromDate, String toDate) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(toDate);
        String afterDay = DateUtils.getAfterDay(parse);

        List<user> userAttendanceRecordDuringSelectDate = attendanceService.findUserAttendanceRecordDuringSelectDate(epc, fromDate, afterDay);
        List<attendance> attendanceList = userAttendanceRecordDuringSelectDate.get(0).getAttendanceList();
        Float attendanceSpan = getAttendanceSpan(attendanceList);

        attendanceCountVo attendanceCountVo = new attendanceCountVo();
        attendanceCountVo.setSpan(attendanceSpan);
        attendanceCountVo.setNickName(userAttendanceRecordDuringSelectDate.get(0).getNickName());
        return attendanceCountVo;
    }


    public Float getAttendanceSpan(List<attendance> attendanceList) throws ParseException {
        long totalDuration = 0;
        long lastEnterTime = 0;
        long lastExitTime = 0;
        boolean lastIsEnter = false;
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        将考勤记录按照时间排序。
//        遍历考勤记录，记录上一个进门记录和出门记录的时间戳，以及是否为进门记录。
//        如果当前记录是出门记录，计算当前记录时间戳和上一个进门记录时间戳之间的时间差，并将结果累加到总时间差中。
//        如果当前记录是进门记录，检查上一个记录是否为出门记录。如果是出门记录，计算出门记录时间戳和当前进门记录时间戳之间的时间差，并将结果累加到总时间差中。
//        如果当前记录是进门记录，记录当前进门记录时间戳为上一个进门记录时间戳。
//        如果当前记录是出门记录，记录当前出门记录时间戳为上一个出门记录时间戳。
//        如果发现当前记录的时间戳早于上一个记录的时间戳，则说明记录顺序不正确，需要进行相应的处理。
        for (attendance record : attendanceList) {
            if (record.getOrientation() == 0) {
//                判断上一个记录也是进门记录的话,则说明进门记录重复了
                if (lastIsEnter==true) {
                    continue;
                }
                // 如果当前进门记录的时间戳早于上一个出门记录的时间戳，则说明顺序不正确
                long thisInTime = format.parse(record.getRecord()).getTime();
                if (format.parse(record.getRecord()).getTime() < lastExitTime) {
                    continue;
                }
                // 记录上一个进门记录时间戳
                lastEnterTime = thisInTime;

            } else {
                if (lastIsEnter==false) { // 如果上一个记录不是进门记录，则说明出门记录重复了
                    // 进行相应的处理，例如将当前记录标记为无效记录
                    continue;
                }
                long thisOutTime = format.parse(record.getRecord()).getTime();
                if (thisOutTime < lastEnterTime) { // 如果当前出门记录的时间戳早于上一个进门记录的时间戳，则说明顺序不正确
                    // 进行相应的处理，例如将当前记录标记为无效记录
                    continue;
                }
                totalDuration += thisOutTime - lastEnterTime; // 计算时间差并累加到总时间差中
                lastExitTime = thisOutTime; // 记录上一个出门记录时间戳
            }
            lastIsEnter = (record.getOrientation() == 0) ? true: false;
        }

        float hour  = totalDuration % nd /nh;
        float minutes=(float) totalDuration % nd %nh/nm/60;
        float result;
        result=hour+minutes;
        result=Float.valueOf(String.format("%.2f", result));
        return result;
    }


}