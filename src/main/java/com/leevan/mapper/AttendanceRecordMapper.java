package com.leevan.mapper;


import com.leevan.vo.attendanceRecordVo;
import com.leevan.vo.countDaysbyMonthVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendanceRecordMapper {


    @Select("select `user`.nick_name, `user`.identity ,`user`.epc,`attendance`.record ,attendance.orientation  from sys_user as user left join sys_attendance as attendance on `user`.epc = attendance.epc ")
    public List<attendanceRecordVo> findAllAttendanceRecord();


    @Select("select `user`.nick_name, `user`.identity ,`user`.epc,`attendance`.record ,attendance.orientation  from sys_user as user left join sys_attendance as attendance on \n" +
            "`user`.epc = attendance.epc where attendance.epc =#{epc}")
    public List<attendanceRecordVo> findAttendanceRecordByEpc(String epc);

    @Select("SELECT sys_user.nick_name, sys_user.identity,t.epc, left(`date`,7) as `month`, COUNT(1) as `day`\n" +
            "        from\n" +
            "        (\n" +
            "        select epc, LEFT(record,10) as `date`\n" +
            "        from sys_attendance\n" +
            "        where left(NOW(),7) = left(record,7)\n" +
            "        GROUP BY epc, `date`\n" +
            "        ) t LEFT JOIN sys_user on sys_user.epc=t.epc\n" +
            "        GROUP BY epc, `month`\n" +
            "        HAVING nick_name IS NOT NULL")
    public List<countDaysbyMonthVo> countDaysbyMonth();
}
