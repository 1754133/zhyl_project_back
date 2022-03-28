package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.WardInfoDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WardInfoMapper {
    @Select("select count(*) from ward_info where ward_id = #{wardId}")
    Integer selectWardNumByWardId(Integer wardId);

    @Select("select bed_id from ward_info where ward_id = #{wardId} and bed_id = #{bedId}")
    Integer selectBedByWardAndBedId(Integer wardId, Integer bedId);

    @Insert("insert into ward_info(ward_id, bed_id, patient_id, principal_id) values(#{wardId}, #{bedId}, #{patientId}, #{principalId})")
    void addPatientWardInfo(Integer wardId, Integer bedId, Integer patientId, Integer principalId);

    @Select("select ward_id, bed_id, patient_id, create_time from ward_info where principal_id = #{doctorId}")
    @Results({
            @Result(column = "ward_id", property = "wardId"),
            @Result(column = "bed_id", property = "bedId"),
            @Result(column = "patient_id", property = "patientId"),
            @Result(column = "create_time", property = "createTime")
    })
    List<WardInfoDTO> selectPatientWardInfoByDoctorId(Integer doctorId);

    @Delete("delete from ward_info where patient_id = #{patientId}")
    boolean deleteWardInfoByPatientId(Integer patientId);
}
