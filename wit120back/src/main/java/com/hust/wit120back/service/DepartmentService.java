package com.hust.wit120back.service;

import com.hust.wit120back.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    public Integer getDepartmentId(Integer departmentId);
    public List<DepartmentDTO> getDepartments();
    public DepartmentDTO getDepartmentsDesc(Integer departmentId);
    List<String> getDepartmentName();
}
