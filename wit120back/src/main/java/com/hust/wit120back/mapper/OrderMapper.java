package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("select * from my_order where patient_id = #{patientId} and doctor_id = #{doctorId} and order_day = #{orderDay} and order_time_slice = #{orderTimeSlice}")
    @Results({
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "patient_id", property = "patientId"),
            @Result(column = "doctor_id", property = "doctorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "order_day", property = "orderDay"),
            @Result(column = "order_time_slice", property = "orderTimeSlice")
    })
    Order selectOrder(Integer patientId, Integer doctorId, int orderDay, int orderTimeSlice);

    @Select("select patients_number from doctor_shift_info where doctor_id = #{doctorId} and day = #{orderDay} and time_slice = #{orderTimeSlice}")
    Integer selectNumAppointmentByDoctorAndTime(Integer doctorId, int orderDay, int orderTimeSlice);

    @Select("select doc_id from doc_info where doc_name = #{doctorName}")
    int selectDoctorIdByName(String doctorName);

    @Select("select user_id from user where username = #{patientName}")
    int selectPatientIdByName(String patientName);

    @Update("update doctor_shift_info set patients_number = patients_number + 1 where doctor_id = #{doctorId} and day = #{orderDay} and time_slice = #{orderTimeSlice}")
    int addPatientsNumber(Integer doctorId, int orderDay, int orderTimeSlice);

    @Insert("insert into my_order(patient_id, doctor_id, order_day, order_time_slice, cost) values (#{patientId}, #{doctorId}, #{orderDay}, #{orderTimeSlice}, #{cost})")
    void addAppointment(Order order);

    @Select("select doctor_id, create_time, order_day, order_time_slice, cost from my_order where patient_id = #{patientId}")
    @Results({
            @Result(column = "doctor_id", property = "doctorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "order_day", property = "orderDay"),
            @Result(column = "order_time_slice", property = "orderTimeSlice")
    })
    List<OrderDTO> selectOrders(Integer patientId);
}
