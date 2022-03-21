package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PrescriptionMapper {
    @Select("select prescription_id from prescription where order_id = #{orderId}")
    Integer selectPrescIdByOrderId(Integer orderId);

    @Select("select prescription from prescription where order_id = #{orderId}")
    String selectPrescByOrderId(Integer orderId);

    @Insert("insert into prescription(order_id, prescription) values(#{orderId}, #{prescription})")
    void addPrescription(Integer orderId, String prescription);

    @Update("update prescription set prescription = #{prescription} where order_id = #{orderId}")
    int updatePrescription(Integer orderId, String prescription);
}
