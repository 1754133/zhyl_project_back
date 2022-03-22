package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MedicalResourceMapper {
    @Select("select medical_res_id from medical_resource where medical_res_name = #{medResName}")
    Integer selectMedResName(String medResName);

    @Select("select medical_res_name from medical_resource where medical_res_id = #{medResId}")
    String selectMedResNameById(Integer medResId);

    @Select("select cost from medical_resource where medical_res_name = #{medResName}")
    Integer selectMedResCost(String medResName);

}
