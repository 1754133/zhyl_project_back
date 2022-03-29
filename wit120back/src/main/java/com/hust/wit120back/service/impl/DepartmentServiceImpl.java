package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.DepartmentDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;

import com.hust.wit120back.entity.Department;

import com.hust.wit120back.entity.DocInfo;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.DepartmentMapper;
import com.hust.wit120back.mapper.DocInfoMapper;
import com.hust.wit120back.mapper.DoctorMapper;
import com.hust.wit120back.service.DepartmentService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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

    @Autowired
    private DoctorMapper doctorMapper;

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

    @Override
    public List<Map<String, Object>> getDocInfoByDepartment(Integer departmentId) {
        List<Map<String, Object>> docInfoList = docInfoMapper.selectDocInfoByDepartmentId(departmentId);
        if (docInfoList.size() == 0){
            throw new ServiceException(Constants.CODE_600, "未找到任何医生信息");
        }
        return docInfoList;
    }

    @Override
    public Map<String, Object> getShiftNumByPage(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int total = departmentMapper.selectTotal();
        if (total == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何科室信息");
        }
        List<Department> departmentList = departmentMapper.selectDepartmentByPage(pageNum, pageSize);
        List<Map<String, Object>> departmentShift = new ArrayList<>();
        List<Integer> shiftDocIdList = doctorMapper.getDocIdList();
        for (Department department : departmentList){
            List<Integer> doctorIdList = departmentMapper.selectDoctorIdByDepartmentId(department.getDepartmentId());
            int doctorNum = doctorIdList.size();
            int noShiftNum = 0;
            for (Integer doctorId : doctorIdList){
                if (!shiftDocIdList.contains(doctorId)){
                    noShiftNum = noShiftNum + 1;
                }
            }
            Map<String, Object> res1 = new HashMap<>();
            res1.put("departmentId", department.getDepartmentId());
            res1.put("departmentName", department.getDepartmentName());
            res1.put("doctorNum", doctorNum);
            res1.put("noShiftNum", noShiftNum);
            departmentShift.add(res1);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", departmentShift);
        res.put("total", total);
        return res;
    }

    @Override
    public List<DocInfo> getNoShiftDoctorByDepartmentId(Integer departmentId) {
        //根据科室ID查询医生信息
        List<DocInfo> docInfoList = docInfoMapper.selectDocInfoListByDepartment(departmentId);
        if (docInfoList.size() == 0){
            throw new ServiceException(Constants.CODE_600, "该科室不存在医生信息");
        }
        //查询坐班表中的所有医生ID
        List<Integer> shiftDocIdList = doctorMapper.getDocIdList();
        if (shiftDocIdList.size() != 0){
            docInfoList.removeIf(docInfo -> shiftDocIdList.contains(docInfo.getDocId()));
        }
        if (docInfoList.size() == 0){
            throw new ServiceException(Constants.CODE_600, "该科室不存在未安排坐诊的医生");
        }
        return docInfoList;
    }

    @Override
    public Map<String, Object> getShiftNumByPageAndName(String departmentName, int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int total = departmentMapper.selectTotalByDepartmentName(departmentName);
        if (total == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何科室信息");
        }
        List<Department> departmentList = departmentMapper.selectDepartmentByPageAndDepartmentName(departmentName, pageNum, pageSize);
        List<Map<String, Object>> departmentShift = new ArrayList<>();
        List<Integer> shiftDocIdList = doctorMapper.getDocIdList();
        for (Department department : departmentList){
            List<Integer> doctorIdList = departmentMapper.selectDoctorIdByDepartmentId(department.getDepartmentId());
            int doctorNum = doctorIdList.size();
            int noShiftNum = 0;
            for (Integer doctorId : doctorIdList){
                if (!shiftDocIdList.contains(doctorId)){
                    noShiftNum = noShiftNum + 1;
                }
            }
            Map<String, Object> res1 = new HashMap<>();
            res1.put("departmentId", department.getDepartmentId());
            res1.put("departmentName", department.getDepartmentName());
            res1.put("doctorNum", doctorNum);
            res1.put("noShiftNum", noShiftNum);
            departmentShift.add(res1);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", departmentShift);
        res.put("total", total);
        return res;
    }

}
