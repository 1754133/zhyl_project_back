package com.hust.wit120back.mapper;

import com.hust.wit120back.dto.DepartmentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("select department_id, department_name from department")
    @Results({
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "department_name", property = "departmentName"),
    })
    List<DepartmentDTO> selectDepartments();
}
