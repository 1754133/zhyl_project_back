package com.hust.wit120back.mapper;

import com.hust.wit120back.entity.PatientInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PatientInfoMapper {
    @Select("select * from patient_info where user_id=#{userId}")
    @Results({
            @Result(column = "patient_info_id", property = "patientInfoId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "identification_num", property = "identificationNum")
    })
    PatientInfo selectPatientInfoByUserId(Integer userId);

    @Insert("insert into patient_info(user_id) values(#{userId})")
    void addPatientInfo(Integer userId);

    @Update("update patient_info set real_name=#{realName},identification_num=#{identificationNum},gender=#{gender},age=#{age} where user_id=#{userId}")
    int updatePatientInfo(PatientInfo patientInfo);

    @Select("select real_name from patient_info where user_id = #{patientId}")
    String selectRealNameById(Integer patientId);

    @Select("select gender from patient_info where user_id = #{patientId}")
    boolean selectGenderById(Integer patientId);

    @Select("select user_id from patient_info where user_id = #{patientId}")
    Integer selectPatientId(Integer patientId);

    @Select("select age from patient_info where user_id = #{patientId}")
    int selectAgeByPatientId(Integer patientId);
}
