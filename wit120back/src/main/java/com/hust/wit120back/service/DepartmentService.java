package com.hust.wit120back.service;

import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;

import java.util.ArrayList;

public interface DepartmentService {
    Integer getDepartmentId(Integer departmentId);
    ArrayList<DepartmentDTO> getDepartments();
    DepartmentDTO getDepartmentsDesc(Integer departmentId);
    ArrayList<ShiftInfoDTO> getDepartShiftInfo(Integer departmentId);
}
