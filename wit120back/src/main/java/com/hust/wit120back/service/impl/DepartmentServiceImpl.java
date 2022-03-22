package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;

import com.hust.wit120back.entity.Department;

import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.DepartmentMapper;
import com.hust.wit120back.mapper.DocInfoMapper;
import com.hust.wit120back.service.DepartmentService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DocInfoMapper docInfoMapper;

    @Override
    public Integer getDepartmentId(Integer departmentId) {
        Integer id = departmentMapper.selectDepartmentById(departmentId);
        return id;
    }

    @Override
    public ArrayList<DepartmentDTO> getDepartments() {
        ArrayList<DepartmentDTO> departments = departmentMapper.selectDepartments();
        return departments;
    }

    @Override
    public DepartmentDTO getDepartmentsDesc(Integer departmentId) {
        DepartmentDTO departments = departmentMapper.selectDepartmentsDesc(departmentId);
        return departments;
    }

    @Override
    public ArrayList<ConciseShiftInfoDTO> getDepartShiftInfo(Integer departmentId) {
        ArrayList<ConciseShiftInfoDTO> shiftInfos = new ArrayList<ConciseShiftInfoDTO>();
        //获取所有医生的id
        ArrayList<Integer> ids = departmentMapper.selectDoctorIdByDepartmentId(departmentId);
        //根据id去查询排班信息
        for (Integer id : ids) {
            //System.out.println("id: " + id);
            ArrayList<ConciseShiftInfoDTO> infos = departmentMapper.selectConciseShiftInfoByDocId(id);
            //System.out.println(infos);
            for (ConciseShiftInfoDTO info : infos) {
                info.setDoctorName(departmentMapper.selectDocNameById(id));
                shiftInfos.add(info);
            }
        }
        if (shiftInfos.size() == 0)
            throw new ServiceException(Constants.CODE_503, "无坐诊信息");
        return shiftInfos;
    }

    @Override
    public List<String> getDepartmentName() {
        return departmentMapper.selectAllDepartmentName();
    }

    @Override
    public Map<String, Object> getDepartmentByPage(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int total = departmentMapper.selectTotal();
        if (total == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何科室信息");
        }
        List<Department> departmentList = departmentMapper.selectDepartmentByPage(pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", departmentList);
        res.put("total", total);
        return res;
    }

    @Override
    public boolean addDepartment(Department department) {
        Integer departmentId = departmentMapper.selectDepartmentIdByDepartmentName(department.getDepartmentName());
        if (departmentId != null){
            throw new ServiceException(Constants.CODE_700, "要添加的科室已经存在");
        }
        departmentMapper.addDepartment(department);
        return true;
    }

    @Override
    public int updateDepartment(Department department) {
        Integer departmentId = departmentMapper.selectDepartmentIdByDepartmentName(department.getDepartmentName());
        if (departmentId == null){
            throw new ServiceException(Constants.CODE_600, "要修改的科室不存在");
        }
        return departmentMapper.updateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(Integer departmentId) {
        String departmentName = departmentMapper.selectDepartmentNameByDepartmentId(departmentId);
        if (departmentName == null){
            throw new ServiceException(Constants.CODE_600, "要删除的科室不存在");
        }
        //把这个科室的所有医生信息中的科室编号置0，代表暂无科室
        List<Integer> docInfoIdList = docInfoMapper.selectDocInfoIdByDepartmentId(departmentId);
        for (Integer docInfoId : docInfoIdList){
            docInfoMapper.updateDepartmentId(0, docInfoId);
        }
        //将科室删除
        departmentMapper.deleteDepartment(departmentId);
        return true;
    }

    @Override
    public Map<String, Object> getDepartmentByPageAndDepartmentName(String departmentName, int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int total = departmentMapper.selectTotalByDepartmentName(departmentName);
        if (total == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何科室信息");
        }
        List<Department> departmentList = departmentMapper.selectDepartmentByPageAndDepartmentName(departmentName, pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", departmentList);
        res.put("total", total);
        return res;
    }

}
