package com.hust.wit120back.mapper;

import com.hust.wit120back.entity.DocInfo;
import org.apache.ibatis.annotations.*;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

@Mapper
public interface DocInfoMapper {
    @Select("select * from doc_info limit #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "doc_info_id", property = "docInfoId"),
            @Result(column = "doc_id", property = "docId"),
            @Result(column = "doc_name", property = "docName"),
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "doc_desc", property = "docDesc"),
            @Result(column = "identification_num", property = "identificationNum")
    })
    List<DocInfo> selectPage(int pageNum, int pageSize);

    @Select("select count(*) from doc_info")
    int selectTotal();

    @Select("select * from doc_info where doc_info_id=#{docInfoId}")
    @Results({
            @Result(column = "doc_info_id", property = "docInfoId"),
            @Result(column = "doc_id", property = "docId"),
            @Result(column = "doc_name", property = "docName"),
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "doc_desc", property = "docDesc"),
            @Result(column = "identification_num", property = "identificationNum")
    })
    DocInfo selectDocInfoByDocInfoId(Integer docInfoId);

    @Update("update doc_info set doc_name=#{docName},department_id=#{departmentId}," +
            "level=#{level},doc_desc=#{docDesc},identification_num=#{identificationNum}," +
            "gender=#{gender},age=#{age} where doc_info_id=#{docInfoId}")
    int updateDocInfo(String docName, Integer departmentId, String level, String docDesc,
                      String identificationNum, boolean gender, int age, Integer docInfoId);

    @Delete("delete from doc_info where doc_info_id=#{docInfoId}")
    int deleteDocInfoByDocInfoId(Integer docInfoId);

    @Insert("insert into doc_info(doc_id) values(#{docId})")
    void addDocInfo(Integer docId);

    @Delete("delete from doc_info where doc_id=#{docId}")
    int deleteDocInfoByDocId(Integer docId);

    @Update("update doc_info set department_id=#{departmentId} where doc_info_id=#{docInfoId}")
    int updateDepartmentId(Integer departmentId, Integer docInfoId);

    @Select("select doc_info_id from doc_info where department_id=#{departmentId}")
    List<Integer> selectDocInfoIdByDepartmentId(Integer departmentId);

    @Select("select * from doc_info where doc_id=#{docId}")
    @Results({
            @Result(column = "doc_info_id", property = "docInfoId"),
            @Result(column = "doc_id", property = "docId"),
            @Result(column = "doc_name", property = "docName"),
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "doc_desc", property = "docDesc"),
            @Result(column = "identification_num", property = "identificationNum")
    })
    DocInfo selectDocInfoByDocId(Integer docId);

    @Select("select doc_name from doc_info where doc_id = #{doctorId}")
    String selectDocNameById(Integer doctorId);

    @Select("select department_id from doc_info where doc_id = #{doctorId}")
    Integer selectDepartmentIdByDocId(Integer doctorId);

    @Select("select * from doc_info where doc_name like concat('%',#{doctorName},'%') limit #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "doc_info_id", property = "docInfoId"),
            @Result(column = "doc_id", property = "docId"),
            @Result(column = "doc_name", property = "docName"),
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "doc_desc", property = "docDesc"),
            @Result(column = "identification_num", property = "identificationNum")
    })
    List<DocInfo> selectPageByDoctorName(String doctorName, int pageNum, int pageSize);

    @Select("select count(*) from doc_info where doc_name like concat('%',#{doctorName},'%')")
    int selectTotalByDoctorName(String doctorName);


    @Select("select doc_id from doc_info where doc_id = #{doctorId}")
    Integer selectDoctorId(Integer doctorId);

    @Select("select doc_id, doc_name, level from doc_info where department_id=#{departmentId}")
    @Results({
            @Result(column = "doc_id", property = "docId"),
            @Result(column = "doc_name", property = "docName"),
    })
    List<Map<String, Object>> selectDocInfoByDepartmentId(Integer departmentId);

    @Select("select doc_id, doc_name from doc_info where department_id=#{departmentId}")
    @Results({
            @Result(column = "doc_id", property = "docId"),
            @Result(column = "doc_name", property = "docName"),
    })
    List<DocInfo> selectDocInfoListByDepartment(Integer departmentId);
}
