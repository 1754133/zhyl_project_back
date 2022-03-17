package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("select department_id from department where department_id = #{departmentId}")
    @Result(column = "department_id", property = "departmentId")
    Integer selectDepartmentById(Integer departmentId);

    @Select("select department_id, department_name from department")
    @Results({
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "department_name", property = "departmentName"),
    })
    List<DepartmentDTO> selectDepartments();

    @Select("select department_name, department_desc from department where department_id = #{departmentId}")
    @Results({
            @Result(column = "department_name", property = "departmentName"),
            @Result(column = "department_desc", property = "departmentDesc")
    })
    DepartmentDTO selectDepartmentsDesc(Integer departmentId);

    @Select("select department_name from department where department_id=#{departmentId}")
    String selectDepartmentNameByDepartmentId(Integer departmentId);

    @Select("select department_id from department where department_name=#{departmentName}")
    Integer selectDepartmentIdByDepartmentName(String departmentName);

    @Select("select department_name from department")
    List<String> selectAllDepartmentName();

}
