package com.hust.wit120back.controller;

import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDTO> getDepartment() {
        List<DepartmentDTO> departments = departmentService.getDepartments();
        for(int i = 0; i < departments.size(); i++){
            DepartmentDTO dep = departments.get(i);
            System.out.println("departmentId: " + dep.getDepartmentId() + " departmentName: " + dep.getDepartmentName());
        }
        return departments;
    }
}
