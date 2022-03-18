package com.hust.wit120back.service;

import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;

import java.util.ArrayList;
import java.util.List;

public interface DepartmentService {
    Integer getDepartmentId(Integer departmentId);
    ArrayList<DepartmentDTO> getDepartments();
    DepartmentDTO getDepartmentsDesc(Integer departmentId);
    ArrayList<ShiftInfoDTO> getDepartShiftInfo(Integer departmentId);
    List<String> getDepartmentName();
}
