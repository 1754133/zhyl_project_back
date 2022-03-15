package com.hust.wit120back.service.impl;

import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.mapper.DepartmentMapper;
import com.hust.wit120back.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Integer getDepartmentId(Integer departmentId){
        Integer id = departmentMapper.selectDepartmentById(departmentId);
        return id;
    }

    @Override
    public List<DepartmentDTO> getDepartments(){
        List<DepartmentDTO> departments = departmentMapper.selectDepartments();
        return departments;
    }

    @Override
    public DepartmentDTO getDepartmentsDesc(Integer departmentId){
        DepartmentDTO departments = departmentMapper.selectDepartmentsDesc(departmentId);
        return departments;
    }

}
