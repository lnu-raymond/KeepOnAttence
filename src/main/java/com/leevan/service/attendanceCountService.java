package com.leevan.service;

import com.leevan.vo.attendanceCountVo;

import java.text.ParseException;

public interface attendanceCountService {

    public attendanceCountVo attendanceCountByDay(String epc,String currentDate) throws ParseException;

    public attendanceCountVo attendanceCountByDate(String epc,String fromDate,String toDate) throws ParseException;
}
