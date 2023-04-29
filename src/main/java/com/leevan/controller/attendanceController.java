package com.leevan.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.gson.Gson;
import com.leevan.Utils.ByteTransferUtil;
import com.leevan.Utils.DateUtils;
import com.leevan.mapper.AttendanceRecordMapper;
import com.leevan.pojo.ServiceRes;
import com.leevan.pojo.attendance;
import com.leevan.pojo.user;
import com.leevan.service.attendanceCountService;
import com.leevan.service.attendanceService;
import com.leevan.vo.Result;
import com.leevan.vo.attendanceCountVo;
import com.leevan.vo.attendanceRecordVo;
import com.leevan.vo.countDaysbyMonthVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;




import static java.net.URLEncoder.encode;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan.controller
 * @className:      attendanceController
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/4/11 23:27
 * @version:    1.0
 */ 
@RestController
@RequestMapping("/attendance")
public class attendanceController {

    @Autowired
    private attendanceService attendanceService;

    @Autowired
    private attendanceCountService attendanceCountService;

    @Autowired
    private AttendanceRecordMapper attendanceRecordMapper;

    @GetMapping("/punchInAndOut")
    public Result punchIn(@RequestParam("epc") String epc,@RequestParam("orientation")Integer orientation) throws IOException {

//        ,@RequestParam("epc")String epc,@RequestParam("orientation") Integer orientation
//        由硬件传递系统签到时间和userId卡号
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        将userId和硬件签到时间插入到数据库,插入数据状态为待完成
//        ByteTransferUtil byteTransferUtil = new ByteTransferUtil();
//        String json = byteTransferUtil.getBodyTxt(request);
//        Gson gson = new Gson();
//        String epc ="12312";
//        Map params = gson.fromJson(json, HashMap.class);
//        String epc = request.getParameter("epc");
//        Integer orientation = Integer.valueOf(request.getParameter("orientation"));
//        Date currentDay = format.parse(currentTime);
        String currentTime = DateUtils.getCurrentTime();
//        String epc1 = params.get("epc").toString();
//        int newOrientation = Integer.parseInt(params.get("orientation").toString());

        ServiceRes serviceRes = attendanceService.PunchInAndOut(epc, currentTime,orientation);
        return Result.build(null,serviceRes.getCode(),serviceRes.getMsg());

    }

//    改造成可以获取所有用户当天的打卡记录
    @RequestMapping("/ObtainRecords")
    public Result ObtainRecordsByUserId(Integer userId)
    {
       return Result.ok(attendanceService.findAllRecordsByuserId(userId));
    }

//    获取给定日期内所有用户考勤数据
//    @RequestMapping("/ObtainAllRecordDuringSelectDate")
//    public Result ObtainAllRecordDuringSelectDate(@RequestParam("fromDate")String fromDate,@RequestParam("toDate")String toDate) throws ParseException {
//        return Result.ok(attendanceService.findUserAttendanceRecordDuringSelectDate(null,fromDate,toDate));
//    }


//    查询出该用户对应的所有考勤记录
    @RequestMapping("/userRecords")
    public Result ObtainRecordsByEpc(@RequestParam("epc") String epc)
    {

        List<attendance> userAttendanceRecord = attendanceService.findUserAttendanceRecordByEpc(epc);
        return Result.ok(userAttendanceRecord);
    }

//    根据日期查询出该用户对应的日期内的考勤记录
    @RequestMapping("/userRecordsDuringGivenDate")
    public Result getUserRecordsDuringGivenDate(@RequestParam("epc") String epc,@RequestParam("fromDate") String fromDate,@RequestParam("toDate")String toDate) throws ParseException {
//        attendanceService.


        return Result.ok(attendanceCountService.attendanceCountByDate(epc,fromDate,toDate));
    }

    @RequestMapping("/countAttendance")
    public attendanceCountVo countAttendace(@RequestParam("record") String record, @RequestParam("epc")String epc) throws ParseException {
        return attendanceCountService.attendanceCountByDay(epc,record);
    }


    @RequestMapping("/countDay")
    public Result countDay(@RequestParam("epc") String epc)
    {
        return Result.ok(attendanceService.countDays(epc));
    }



//    @PostMapping("/punchOut")
//    public Result punchOut(@RequestParam("userId") Integer userId,@RequestParam("lastPunchInTime") String lastPunchInTime,@RequestParam("currentTime") String currentTime )
//    {
////        由硬件传递系统上次签到时间和userId卡号
////        根据上次签到时间和userId到数据库查询记录
////       计算签到和签退两次时间的差值,获取出勤时间
////        获取当前系统时间
////        String currentTime = DateUtils.getCurrentTime();
//        ServiceRes serviceRes = attendanceService.PunchOut(userId,lastPunchInTime,currentTime);
//        return Result.build(null,serviceRes.getCode(),serviceRes.getMsg());
//
//    }
    @RequestMapping("/attendanceRecordsWithNickName")
    public Result getattendanceRecordWithNickName()
    {
        return Result.ok(attendanceRecordMapper.findAllAttendanceRecord());
    }

    @RequestMapping("/attendanceRecordsWithNickNamebyEpc")
    public Result getAttendanceRecordWithNickNameByEpc(@RequestParam("epc") String epc)
    {
        return Result.ok(attendanceRecordMapper.findAttendanceRecordByEpc(epc));
    }

    @RequestMapping("/export")
    public Result exportAttendanceByMonth(HttpServletResponse response) throws IOException {
        List<countDaysbyMonthVo> countDaysbyMonthVos = attendanceRecordMapper.countDaysbyMonth();
        ExcelWriter writer = ExcelUtil.getWriter( true);
        writer.addHeaderAlias("day","出勤天数");
        writer.addHeaderAlias("nickName","员工姓名");
        writer.addHeaderAlias("identity","身份");
        writer.addHeaderAlias("epc","卡号");
        writer.addHeaderAlias("month","月份");

        writer.write(countDaysbyMonthVos,true);
        response.setContentType("application/vnd.apenxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("员工月出勤统计表","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+ fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();;
        writer.close();

        return Result.ok();
    }

    @RequestMapping("/exportAllAttendanceRecord")
    public Result exportAllAttendanceRecord(HttpServletResponse response) throws IOException {
        List<attendanceRecordVo> allAttendanceRecord = attendanceRecordMapper.findAllAttendanceRecord();

        ExcelWriter writer = ExcelUtil.getWriter( true);
        writer.addHeaderAlias("record","打卡时间");
        writer.addHeaderAlias("nickName","员工姓名");
        writer.addHeaderAlias("identity","身份");
        writer.addHeaderAlias("epc","卡号");
        writer.addHeaderAlias("orientation","方向(0标识为进入 1标识为出去)");
        writer.write(allAttendanceRecord,true);
        response.setContentType("application/vnd.apenxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("员工出勤所有记录表","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+ fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();;
        writer.close();
        return Result.ok();



    }

}