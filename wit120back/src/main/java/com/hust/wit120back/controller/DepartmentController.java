package com.hust.wit120back.controller;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public Result getDepartment() {
        ArrayList<DepartmentDTO> departments = departmentService.getDepartments();
        for(int i = 0; i < departments.size(); i++){
            DepartmentDTO dep = departments.get(i);
            //System.out.println("departmentId: " + dep.getDepartmentId() + " departmentName: " + dep.getDepartmentName());
        }
        return Result.success(departments);
    }

    @GetMapping("/description")
    public Result getDepartmentDesc(@RequestParam Integer departmentId){
        //System.out.println("departmentId: " + departmentId);
        DepartmentDTO department = departmentService.getDepartmentsDesc(departmentId);
        //判断一下id是否合法
        if(departmentService.getDepartmentId(departmentId) == null){
            return Result.error(Constants.CODE_403, "该科室不存在");
        }
        //System.out.println("departmentName: " + department.getDepartmentName() + " departmentDesc: " + department.getDepartmentDesc());
        return Result.success(department);
    }

    @GetMapping("/shiftInfo")
    public Result getShiftInfo(@RequestParam Integer departmentId){
        ArrayList<ShiftInfoDTO> shiftInfos;
        try{
            shiftInfos = departmentService.getDepartShiftInfo(departmentId);
        }catch (ServiceException e){
            return Result.error(Constants.CODE_503, "无坐诊信息");
        }
        return Result.success(shiftInfos);
    }

}
