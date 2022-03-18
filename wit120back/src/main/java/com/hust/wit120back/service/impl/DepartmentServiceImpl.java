package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.DepartmentMapper;
import com.hust.wit120back.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


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
    public ArrayList<DepartmentDTO> getDepartments(){
        ArrayList<DepartmentDTO> departments = departmentMapper.selectDepartments();
        return departments;
    }

    @Override
    public DepartmentDTO getDepartmentsDesc(Integer departmentId){
        DepartmentDTO departments = departmentMapper.selectDepartmentsDesc(departmentId);
        return departments;
    }

    @Override
    public ArrayList<ShiftInfoDTO> getDepartShiftInfo(Integer departmentId){
        ArrayList<ShiftInfoDTO> shiftInfos = new ArrayList<ShiftInfoDTO>();
        //获取所有医生的id
        ArrayList<Integer> ids = departmentMapper.selectDoctorIdByDepartmentId(departmentId);
        //根据id去查询排班信息
        for(Integer id : ids){
            System.out.println("id: " + id);
            ArrayList<ShiftInfoDTO> infos = departmentMapper.selectShiftInfoByDocId(id);
            System.out.println(infos);
            for(ShiftInfoDTO info : infos){
                info.setDoctorName(departmentMapper.selectDocNameById(id));
                shiftInfos.add(info);
            }
        }
        if(shiftInfos.size() == 0)
            throw new ServiceException(Constants.CODE_503, "无坐诊信息");
        return shiftInfos;
    }




}
