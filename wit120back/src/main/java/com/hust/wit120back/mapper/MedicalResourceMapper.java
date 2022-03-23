package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MedicalResourceMapper {
    @Select("select medical_res_id from medical_resource where medical_res_name = #{medResName}")
    Integer selectMedResName(String medResName);

    @Select("select medical_res_name from medical_resource where medical_res_id = #{medResId}")
    String selectMedResNameById(Integer medResId);

    @Select("select cost from medical_resource where medical_res_name = #{medResName}")
    Integer selectMedResCost(String medResName);

    @Select("select medical_res_name, medical_res_id from medical_resource")
    List<Map<String, Integer>> selectMedResNameAndId();

}
