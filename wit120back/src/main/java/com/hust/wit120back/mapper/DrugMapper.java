package com.hust.wit120back.mapper;

import com.hust.wit120back.entity.Drug;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DrugMapper {
    @Select("select * from drug limit #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "drug_id", property = "drugId"),
            @Result(column = "drug_name", property = "drugName"),
            @Result(column = "approval_num", property = "approvalNum")
    })
    List<Drug> selectDrugByPage(int pageNum, int pageSize);

    @Select("select count(*) from drug")
    int selectTotal();

    @Select("select * from drug where drug_name=#{drugName} and manufacturer=#{manufacturer}")
    Drug selectDrugByDrugNameAndManufacturer(String drugName, String manufacturer);

    @Insert("insert into drug(drug_name,approval_num,formulation,specification,manufacturer,category,cost)" +
            "values(#{drugName},#{approvalNum},#{formulation},#{specification},#{manufacturer},#{category},#{cost})")
    void addDrug(Drug drug);

    @Select("select * from drug where drug_id=#{drugId}")
    Drug selectDrugByDrugId(Integer drugId);

    @Update("update drug set drug_name=#{drugName},approval_num=#{approvalNum},formulation=#{formulation}," +
            "specification=#{specification},manufacturer=#{manufacturer},category=#{category},cost=#{cost}" +
            "where drug_id=#{drugId}")
    int updateDrug(Drug drug);

    @Delete("delete from drug where drug_id=#{drugId}")
    int deleteDrugByDrugId(Integer drugId);

    @Select("select count(*) from drug where drug_name like concat('%',#{drugName},'%')")
    int selectTotalByDrugName(String drugName);

    @Select("select * from drug where drug_name like concat('%',#{drugName},'%') limit #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "drug_id", property = "drugId"),
            @Result(column = "drug_name", property = "drugName"),
            @Result(column = "approval_num", property = "approvalNum")
    })
    List<Drug> selectDrugByPageAndDrugName(String drugName, int pageNum, int pageSize);

    @Select("select drug_name from drug")
    List<String> selectAllDrugNames();
}
