package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
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

    @Insert("insert into my_order(patient_id, doctor_id, order_day, order_time_slice, cost) values (#{patientId}, #{doctorId}, #{orderDay}, #{orderTimeSlice}, #{cost})")
    void addAppointment(Order order);

    @Select("select order_id, doctor_id, create_time, order_day, order_time_slice, cost from my_order where patient_id = #{patientId}")
    @Results({
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "doctor_id", property = "doctorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "order_day", property = "orderDay"),
            @Result(column = "order_time_slice", property = "orderTimeSlice")
    })
    List<OrderDTO> selectOrders(Integer patientId);

    @Select("select order_id, patient_id, doctor_id, create_time, order_day, order_time_slice, cost from my_order where doctor_id = #{doctorId}")
    @Results({
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "patient_id", property = "patientId"),
            @Result(column = "doctor_id", property = "doctorId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "order_day", property = "orderDay"),
            @Result(column = "order_time_slice", property = "orderTimeSlice")
    })
    List<OrderDTO> selectOrdersByDocId(Integer doctorId);

    @Select("select order_id from my_order where order_id = #{orderId}")
    Integer selectOrderId(Integer orderId);

    @Select("select doctor_id from my_order where order_id = #{orderId}")
    Integer selectDoctorIdByOrderId(Integer orderId);

    @Select("select order_day from my_order where order_id = #{orderId}")
    int selectOrderDayByOrderId(Integer orderId);

    @Select("select order_time_slice from my_order where order_id = #{orderId}")
    int selectOrderTimeSliceByOrderId(Integer orderId);

    @Delete("delete from my_order where order_id = #{orderId}")
    void deleteOrder(Integer orderId);
}
