package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DoctorShiftInfoMapper {
    @Select("select patients_number from doctor_shift_info where doctor_id = #{doctorId} and day = #{orderDay} and time_slice = #{orderTimeSlice}")
    Integer selectNumAppointmentByDoctorAndTime(Integer doctorId, int orderDay, int orderTimeSlice);

    @Update("update doctor_shift_info set patients_number = patients_number + 1 where doctor_id = #{doctorId} and day = #{orderDay} and time_slice = #{orderTimeSlice}")
    int addPatientsNumber(Integer doctorId, int orderDay, int orderTimeSlice);

    @Update("update doctor_shift_info set patients_number = patients_number - 1 where doctor_id = #{doctorId} and day = #{orderDay} and time_slice = #{orderTimeSlice}")
    void deletePatientNumber(Integer doctorId, int orderDay, int orderTimeSlice);


}
