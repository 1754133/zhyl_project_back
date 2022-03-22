package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.MedResOrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MedicalResourceOrderMapper {
    @Select("select med_res_order_id from medical_resource_order where order_id = #{orderId} and medical_res_id = #{medResId}")
    Integer selectMedResOrderId(Integer orderId, Integer medResId);

    @Select("select count(*) from medical_resource_order where day = #{day} and noon = #{noon} and medical_res_id = #{medResId}")
    int selectOrderNumber(int day, int noon, Integer medResId);

    @Insert("insert into medical_resource_order(order_id, patient_id, day, noon, cost, medical_res_id) values(#{orderId}, " +
            "#{patientId}, #{day}, #{noon}, #{cost}, #{medResId})")
    void addAppointment(MedResOrderDTO medResOrderDTO);
}
