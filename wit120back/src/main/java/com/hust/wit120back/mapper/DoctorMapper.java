package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface DoctorMapper {
    @Select("select doctor_id, day, noon from doctor_concise_shift_info where doctor_id = #{doctorId}")
    @Result(column = "doctor_id", property = "doctorId")
    ArrayList<ConciseShiftInfoDTO> selectDocConciseShiftInfoById(Integer doctorId);

    @Select("select doctor_id, day, time_slice from doctor_shift_info where doctor_id = #{doctorId}")
    @Results({
            @Result(column = "doctor_id", property = "doctorId"),
            @Result(column = "day", property = "orderDay"),
            @Result(column = "time_slice", property = "orderTimeSlice")
    })
    ArrayList<ShiftInfoDTO> selectDocShiftInfoById(Integer doctorId);
}
