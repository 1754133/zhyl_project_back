package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WardInfoMapper {
    @Select("select count(*) from ward_info where ward_id = #{wardId}")
    Integer selectWardNumByWardId(Integer wardId);

    @Select("select bed_id from ward_info where ward_id = #{wardId} and bed_id = #{bedId}")
    Integer selectBedByWardAndBedId(Integer wardId, Integer bedId);

    @Insert("insert into ward_info(ward_id, bed_id, patient_id, principal_id) values(#{wardId}, #{bedId}, #{patientId}, #{principalId})")
    void addPatientWardInfo(Integer wardId, Integer bedId, Integer patientId, Integer principalId);
}
