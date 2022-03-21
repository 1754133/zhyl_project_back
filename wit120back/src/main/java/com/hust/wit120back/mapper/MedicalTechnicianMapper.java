package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.MedicalTechnicianDTO;
import com.hust.wit120back.entity.MedicalTechnician;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MedicalTechnicianMapper {
    @Select("select * from medical_technician limit #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "technician_id", property = "technicianId"),
            @Result(column = "technician_name", property = "technicianName"),
            @Result(column = "doc_id", property = "docId")
    })
    List<MedicalTechnician> selectMedicalTechnicianByPage(int pageNum, int pageSize);

    @Select("select count(*) from medical_technician")
    int selectTotal();

    @Select("select * from medical_technician where doc_id=#{docId}")
    @Results({
            @Result(column = "technician_id", property = "technicianId"),
            @Result(column = "technician_name", property = "technicianName"),
            @Result(column = "doc_id", property = "docId")
    })
    MedicalTechnician selectMedicalTechnicianByDocId(Integer docId);

    @Select("select * from medical_technician where technician_id=#{technicianId}")
    @Results({
            @Result(column = "technician_id", property = "technicianId"),
            @Result(column = "technician_name", property = "technicianName"),
            @Result(column = "doc_id", property = "docId")
    })
    MedicalTechnician selectMedicalTechnicianByTechnicianId(Integer technicianId);

    @Select("select * from medical_technician where technician_name=#{technicianName}")
    @Results({
            @Result(column = "technician_id", property = "technicianId"),
            @Result(column = "technician_name", property = "technicianName"),
            @Result(column = "doc_id", property = "docId")
    })
    MedicalTechnician selectMedicalTechnicianByTechnicianName(String technicianName);

    @Insert("insert into medical_technician(technician_name, doc_id, cost) values(#{technicianName}, #{docId}, #{cost})")
    void addMedicalTechnician(MedicalTechnician medicalTechnician);

    @Update("update medical_technician set technician_name=#{technicianName}, doc_id=#{docId}, cost=#{cost} where technician_id=#{technicianId}")
    int updateMedicalTechnician(MedicalTechnician medicalTechnician);

    @Delete("delete from medical_technician where technician_id=#{technicianId}")
    int deleteMedicalTechnicianById(Integer technicianId);
}
