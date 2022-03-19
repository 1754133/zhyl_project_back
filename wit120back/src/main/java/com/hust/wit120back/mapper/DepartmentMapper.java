package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.hust.wit120back.entity.Department;
import org.apache.ibatis.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("select department_id from department where department_id = #{departmentId}")
    Integer selectDepartmentById(Integer departmentId);

    @Select("select department_id, department_name from department")
    @Results({
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "department_name", property = "departmentName"),
    })
    ArrayList<DepartmentDTO> selectDepartments();

    @Select("select department_name, department_desc from department where department_id = #{departmentId}")
    @Results({
            @Result(column = "department_name", property = "departmentName"),
            @Result(column = "department_desc", property = "departmentDesc")
    })
    DepartmentDTO selectDepartmentsDesc(Integer departmentId);

    @Select("select doc_id from doc_info where department_id = #{departmentId}")
    ArrayList<Integer> selectDoctorIdByDepartmentId(Integer departmentId);

    @Select("select doctor_id, day, noon from doctor_concise_shift_info where doctor_id = #{doctorId}")
    @Result(column = "doctor_id", property = "doctorId")
    ArrayList<ConciseShiftInfoDTO> selectConciseShiftInfoByDocId(Integer doctorId);

    @Select("select doc_name from doc_info where doc_id = #{doctorId}")
    String selectDocNameById(Integer doctorId);

    @Select("select department_name from department where department_id=#{departmentId}")
    String selectDepartmentNameByDepartmentId(Integer departmentId);

    @Select("select department_id from department where department_name=#{departmentName}")
    Integer selectDepartmentIdByDepartmentName(String departmentName);

    @Select("select department_name from department")
    List<String> selectAllDepartmentName();

    @Select("select * from department limit #{pageNum},#{pageSize}")
    @Results({
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "department_name", property = "departmentName"),
            @Result(column = "department_desc", property = "departmentDesc")
    })
    List<Department> selectDepartmentByPage(int pageNum, int pageSize);

    @Select("select count(*) from department")
    int selectTotal();

    @Insert("insert into department(department_name, department_desc) values(#{departmentName}, #{departmentDesc})")
    void addDepartment(Department department);

    @Update("update department set department_name=#{departmentName}, department_desc=#{departmentDesc} where department_id=#{departmentId}")
    int updateDepartment(Department department);

    @Delete("delete from department where department_id=#{departmentId}")
    int deleteDepartment(Integer departmentId);
}
