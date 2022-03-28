package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.entity.Department;
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

    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result getDepartmentByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        return Result.success(departmentService.getDepartmentByPage(pageNum, pageSize));
    }

    /**
     * 根据科室名模糊查询科室
     * @param departmentName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{departmentName}/{pageNum}/{pageSize}")
    public Result getDepartmentByPageAndDepartmentName(@PathVariable String departmentName, @PathVariable int pageNum, @PathVariable int pageSize){
        if (departmentName == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(departmentService.getDepartmentByPageAndDepartmentName(departmentName, pageNum, pageSize));
    }

    @GetMapping("/description/{departmentId}")
    public Result getDepartmentDesc(@PathVariable Integer departmentId){
        //System.out.println("departmentId: " + departmentId);
        DepartmentDTO department = departmentService.getDepartmentsDesc(departmentId);
        //判断一下id是否合法
        if(departmentService.getDepartmentId(departmentId) == null){
            return Result.error(Constants.CODE_403, "该科室不存在");
        }
        //System.out.println("departmentName: " + department.getDepartmentName() + " departmentDesc: " + department.getDepartmentDesc());
        return Result.success(department);
    }

    @GetMapping("/shiftInfo/{departmentId}")
    public Result getShiftInfo(@PathVariable Integer departmentId){
        ArrayList<ConciseShiftInfoDTO> shiftInfos;
        try{
            shiftInfos = departmentService.getDepartShiftInfo(departmentId);
        }catch (ServiceException e){
            return Result.error(Constants.CODE_503, "无坐诊信息");
        }
        return Result.success(shiftInfos);
    }

    /**
     * 分页查询每个科室的总医生人数和未排班医生人数
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/shift/{pageNum}/{pageSize}")
    public Result getShiftNumByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        return Result.success(departmentService.getShiftNumByPage(pageNum, pageSize));
    }

    /**
     * 查询该科室未安排坐诊的所有医生信息
     * @param departmentId
     * @return
     */
    @GetMapping("/shift/doctorName/{departmentId}")
    public Result getNoShiftDoctorByDepartmentId(@PathVariable Integer departmentId){
        if (departmentId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(departmentService.getNoShiftDoctorByDepartmentId(departmentId));
    }

    @GetMapping("/doc/{departmentId}")
    public Result getDocInfoByDepartment(@PathVariable Integer departmentId){
        if (departmentId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(departmentService.getDocInfoByDepartment(departmentId));
    }

    @GetMapping("/name")
    public Result getDepartmentName(){
        return Result.success(departmentService.getDepartmentName());
    }

    @PostMapping
    public Result addDepartment(@RequestBody Department department){
        String departmentName = department.getDepartmentName();
        String departmentDesc = department.getDepartmentDesc();
        if (StrUtil.isBlank(departmentName) || StrUtil.isBlank(departmentDesc)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(departmentService.addDepartment(department));
    }

    @PutMapping
    public Result updateDepartment(@RequestBody Department department){
        String departmentName = department.getDepartmentName();
        String departmentDesc = department.getDepartmentDesc();
        if (StrUtil.isBlank(departmentName) || StrUtil.isBlank(departmentDesc)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(departmentService.updateDepartment(department));
    }

    @DeleteMapping("/{departmentId}")
    public Result deleteDepartment(@PathVariable Integer departmentId){
        if (departmentId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(departmentService.deleteDepartment(departmentId));
    }
}
