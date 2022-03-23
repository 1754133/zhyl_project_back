package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.MedResOrderDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MedicalResourceOrderMapper {
    @Select("select med_res_order_id from medical_resource_order where order_id = #{orderId} and medical_res_id = #{medResId}")
    Integer selectMedResOrderId(Integer orderId, Integer medResId);

    @Select("select count(*) from medical_resource_order where day = #{day} and noon = #{noon} and medical_res_id = #{medResId}")
    int selectOrderNumber(int day, int noon, Integer medResId);

    @Insert("insert into medical_resource_order(order_id, patient_id, day, noon, cost, medical_res_id) values(#{orderId}, " +
            "#{patientId}, #{day}, #{noon}, #{cost}, #{medResId})")
    void addAppointment(MedResOrderDTO medResOrderDTO);

    @Select("select med_res_order_id, order_id, create_time, day, noon, cost, medical_res_id from medical_resource_order " +
            "where patient_id = #{patientId}")
    @Results({
            @Result(column = "med_res_order_id", property = "medResOrderId"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "medical_res_id", property = "medResId")
    })
    List<MedResOrderDTO> selectAllMedResOrdersByPatientId(Integer patientId);

    @Select("select med_res_order_id, order_id, create_time, day, noon, cost, medical_res_id from medical_resource_order " +
            "where patient_id = #{patientId} and order_id = #{orderId}")
    @Results({
            @Result(column = "med_res_order_id", property = "medResOrderId"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "medical_res_id", property = "medResId")
    })
    List<MedResOrderDTO> selectMedResOrderByPatientAndOrderId(Integer patientId, Integer orderId);

    @Select("select med_res_order_id, order_id, patient_id, create_time, day, noon, cost, medical_res_id from medical_resource_order " +
            "where medical_res_id = #{medResId}")
    @Results({
            @Result(column = "med_res_order_id", property = "medResOrderId"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "patient_id", property = "patientId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "medical_res_id", property = "medResId")
    })
    List<MedResOrderDTO> selectMedResOrderByMedResId(Integer medResId);
}
