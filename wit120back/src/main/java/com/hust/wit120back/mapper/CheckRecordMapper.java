package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface CheckRecordMapper {
    @Select("select patient_id from check_record where patient_id = #{patientId}")
    Integer selectPatientIdByItself(Integer patientId);

    @Insert("insert into check_record(patient_id, check_record) values(#{patientId}, #{checkResult})")
    void addCheckResult(Integer patientId, String checkResult);

    @Update("update check_record set check_record = #{checkResult} where patient_id = #{patientId}")
    void updateCheckResult(Integer patientId, String checkResult);
}
