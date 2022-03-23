package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.MedicalTechnicianDTO;
import com.hust.wit120back.entity.MedicalTechnician;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    @Select("select count(*) from medical_technician where technician_name like concat('%',#{technicianName},'%')")
    int selectTotalByTechnicianName(String technicianName);

    @Select("select * from medical_technician where technician_name like concat('%',#{technicianName},'%') limit #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "technician_id", property = "technicianId"),
            @Result(column = "technician_name", property = "technicianName"),
            @Result(column = "doc_id", property = "docId")
    })
    List<MedicalTechnician> selectMedicalTechnicianByPageAndTechnicianName(String technicianName, int pageNum, int pageSize);

    @Select("select technician_name from medical_technician")
    List<String> selectAllTechnicianName();


    @Select("select technician_id from medical_technician where technician_name = #{technician}")
    Integer selectTechnicianName(String technicianName);

    @Select("select technician_name from medical_technician where technician_id = #{technicianId}")
    String selectTechnicianNameById(Integer technicianId);

    @Select("select cost from medical_technician where technician_name = #{technicianName}")
    Integer selectCost(String technicianName);

    @Select("select technician_name, technician_id from medical_technician")
    List<Map<String, Integer>> selectTechniciansNameAndId();

    @Select("select technician_id from medical_technician where doc_id = #{doctorId}")
    Integer selectTechnicianIdByDocId(Integer doctorId);
}
