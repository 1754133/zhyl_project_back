package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.WardInfoDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WardInfoMapper {
    @Select("select count(*) from ward_info where ward_id = #{wardId}")
    Integer selectWardNumByWardId(Integer wardId);

    @Select("select bed_id from ward_info where ward_id = #{wardId} and bed_id = #{bedId}")
    Integer selectBedByWardAndBedId(Integer wardId, Integer bedId);

    @Insert("insert into ward_info(ward_id, bed_id, patient_id, principal_id) values(#{wardId}, #{bedId}, #{patientId}, #{principalId})")
    void addPatientWardInfo(Integer wardId, Integer bedId, Integer patientId, Integer principalId);

    @Select("select * from ward_info where patient_id=#{patientId}")
    @Results({
            @Result(column = "ward_info_id", property = "wardInfoId"),
            @Result(column = "ward_id", property = "wardId"),
            @Result(column = "bed_id", property = "bedId"),
            @Result(column = "patient_id", property = "patientId"),
            @Result(column = "principal_id", property = "principalId")
    })
    WardInfoDTO selectWardInfoByPatientId(Integer patientId);
}
