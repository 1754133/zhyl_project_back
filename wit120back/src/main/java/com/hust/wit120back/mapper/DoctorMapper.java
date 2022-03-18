package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface DoctorMapper {
    @Select("select doctor_id, day, noon from doctor_concise_shift_info where doctor_id = #{doctorId}")
    @Result(column = "doctor_id", property = "doctorId")
    ArrayList<ConciseShiftInfoDTO> selectDocShinftInfoById(Integer doctorId);
}
