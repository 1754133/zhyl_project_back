package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.DocInfoDTO;
import com.hust.wit120back.entity.Department;
import com.hust.wit120back.entity.DocInfo;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.DepartmentMapper;
import com.hust.wit120back.mapper.DocInfoMapper;
import com.hust.wit120back.service.DocInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocInfoServiceImpl implements DocInfoService {
    @Autowired
    private DocInfoMapper docInfoMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Map<String, Object> selectPage(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int count = docInfoMapper.selectTotal();
        if (count == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何医生信息");
        }
        List<DocInfo> docInfoList = docInfoMapper.selectPage(pageNum, pageSize);
        List<DocInfoDTO> docInfoDTOList = new ArrayList<>();
        for (DocInfo docInfo : docInfoList){
            DocInfoDTO docInfoDTO = new DocInfoDTO();
            docInfoDTO.setDocInfoId(docInfo.getDocInfoId());
            docInfoDTO.setDocName(docInfo.getDocName());
            docInfoDTO.setLevel(docInfo.getLevel());
            docInfoDTO.setDocDesc(docInfo.getDocDesc());
            docInfoDTO.setIdentificationNum(docInfo.getIdentificationNum());
            docInfoDTO.setGender(docInfo.isGender());
            docInfoDTO.setAge(docInfo.getAge());
            String department = departmentMapper.selectDepartmentNameByDepartmentId(docInfo.getDepartmentId());
            if (department == null){
                department = "暂无";
            }
            docInfoDTO.setDepartment(department);
            docInfoDTOList.add(docInfoDTO);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", docInfoDTOList);
        res.put("total", count);
        return res;
    }

    @Override
    public int updateDocInfo(DocInfoDTO docInfoDTO) {
        if (docInfoMapper.selectDocInfoByDocInfoId(docInfoDTO.getDocInfoId()) == null){
            throw new ServiceException(Constants.CODE_600, "该条医生信息不存在");
        }
        Integer departmentId = departmentMapper.selectDepartmentIdByDepartmentName(docInfoDTO.getDepartment());
        if (departmentId == null){
            throw new ServiceException(Constants.CODE_600, "科室名字不存在");
        }
        return docInfoMapper.updateDocInfo(docInfoDTO.getDocName(), departmentId, docInfoDTO.getLevel(),
                docInfoDTO.getDocDesc(), docInfoDTO.getIdentificationNum(), docInfoDTO.isGender(),
                docInfoDTO.getAge(), docInfoDTO.getDocInfoId());
    }

    @Override
    public int deleteDocInfo(Integer docInfoId) {
        if (docInfoMapper.selectDocInfoByDocInfoId(docInfoId) == null){
            throw new ServiceException(Constants.CODE_600, "要删除的医生信息不存在");
        }
        return docInfoMapper.deleteDocInfoByDocInfoId(docInfoId);
    }

    @Override
    public DocInfoDTO getDocInfoByDocId(Integer docId) {
        DocInfo docInfo = docInfoMapper.selectDocInfoByDocId(docId);
        if (docInfo == null){
            throw new ServiceException(Constants.CODE_600, "要查询的科室信息不存在");
        }
        String department = departmentMapper.selectDepartmentNameByDepartmentId(docInfo.getDepartmentId());
        if (department == null){
            department = "暂无";
        }
        DocInfoDTO docInfoDTO = new DocInfoDTO();
        docInfoDTO.setAge(docInfo.getAge());
        docInfoDTO.setDocDesc(docInfo.getDocDesc());
        docInfoDTO.setDocName(docInfo.getDocName());
        docInfoDTO.setDepartment(department);
        docInfoDTO.setLevel(docInfo.getLevel());
        docInfoDTO.setGender(docInfo.isGender());
        return docInfoDTO;
    }
}
