package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

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

    @Select("select time_slice, patients_number from doctor_shift_info where doctor_id=#{doctorId} and day=#{orderDay} and time_slice<=3")
    @Results({
            @Result(column = "time_slice", property = "orderTimeSlice"),
            @Result(column = "patients_number", property = "patientsNumber")
    })
    List<ShiftInfoDTO> selectDocShiftInfoByIdAndDayAndMor(Integer doctorId, int orderDay);

    @Select("select time_slice, patients_number from doctor_shift_info where doctor_id=#{doctorId} and day=#{orderDay} and time_slice>=4")
    @Results({
            @Result(column = "time_slice", property = "orderTimeSlice"),
            @Result(column = "patients_number", property = "patientsNumber")
    })
    List<ShiftInfoDTO> selectDocShiftInfoByIdAndDayAndAft(Integer doctorId, int orderDay);

    @Select("select distinct doctor_id from doctor_concise_shift_info")
    List<Integer> getDocIdList();
}
