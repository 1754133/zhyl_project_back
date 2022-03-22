package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.MedicalTechnicianDTO;
import com.hust.wit120back.entity.MedicalTechnician;
import com.hust.wit120back.entity.User;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.MedicalTechnicianMapper;
import com.hust.wit120back.mapper.UserMapper;
import com.hust.wit120back.service.MedicalTechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicalTechnicianServiceImpl implements MedicalTechnicianService {
    @Autowired
    private MedicalTechnicianMapper medicalTechnicianMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询医技条目
     * @param pageNum 页号
     * @param pageSize 每页的信息条数
     * @return map
     */
    @Override
    public Map<String, Object> getMedicalTechnicianByPage(int pageNum, int pageSize) {
        //对页数进行处理
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int total = medicalTechnicianMapper.selectTotal();
        if (total == 0){
            throw new ServiceException(Constants.CODE_600, "未找到任何医技信息");
        }
        List<MedicalTechnician> medicalTechnicianList = medicalTechnicianMapper.selectMedicalTechnicianByPage(pageNum, pageSize);
        List<MedicalTechnicianDTO> medicalTechnicianDTOList = new ArrayList<>();
        for (MedicalTechnician medicalTechnician : medicalTechnicianList){
            MedicalTechnicianDTO medicalTechnicianDTO = new MedicalTechnicianDTO();
            medicalTechnicianDTO.setTechnicianId(medicalTechnician.getTechnicianId());
            medicalTechnicianDTO.setTechnicianName(medicalTechnician.getTechnicianName());
            medicalTechnicianDTO.setCost(medicalTechnician.getCost());
            User user = userMapper.selectUserByUserId(medicalTechnician.getDocId());
            if (user == null){
                throw new ServiceException(Constants.CODE_600, "负责帐号不存在");
            }
            medicalTechnicianDTO.setUsername(user.getUsername());
            medicalTechnicianDTOList.add(medicalTechnicianDTO);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", medicalTechnicianDTOList);
        res.put("total", total);
        return res;
    }

    /**
     * 增加医技信息
     * @param medicalTechnicianDTO
     * @return
     */
    @Override
    public boolean addMedicalTechnician(MedicalTechnicianDTO medicalTechnicianDTO) {
        if (medicalTechnicianMapper.selectMedicalTechnicianByTechnicianName(medicalTechnicianDTO.getTechnicianName()) != null){
            throw new ServiceException(Constants.CODE_700, "要增加的医技已经存在");
        }
        MedicalTechnician medicalTechnician = new MedicalTechnician();
        medicalTechnician.setTechnicianName(medicalTechnicianDTO.getTechnicianName());
        medicalTechnician.setCost(medicalTechnicianDTO.getCost());
        User user = userMapper.selectUserByUsername(medicalTechnicianDTO.getUsername());
        if (user == null){
            throw new ServiceException(Constants.CODE_600, "负责帐号不存在");
        }
        Integer docId = user.getUserId();
        medicalTechnician.setDocId(docId);
        medicalTechnicianMapper.addMedicalTechnician(medicalTechnician);
        return true;
    }

    /**
     * 修改医技信息
     * @param medicalTechnicianDTO
     * @return
     */
    @Override
    public int updateMedicalTechnician(MedicalTechnicianDTO medicalTechnicianDTO) {
        MedicalTechnician medicalTechnician = medicalTechnicianMapper.selectMedicalTechnicianByTechnicianId(medicalTechnicianDTO.getTechnicianId());
        if (medicalTechnician == null){
            throw new ServiceException(Constants.CODE_600, "要修改的医技信息不存在");
        }
        MedicalTechnician medicalTechnician1 = new MedicalTechnician();
        medicalTechnician1.setTechnicianId(medicalTechnicianDTO.getTechnicianId());
        medicalTechnician1.setTechnicianName(medicalTechnicianDTO.getTechnicianName());
        medicalTechnician1.setCost(medicalTechnicianDTO.getCost());
        User user = userMapper.selectUserByUsername(medicalTechnicianDTO.getUsername());
        if (user == null){
            throw new ServiceException(Constants.CODE_600, "负责帐号不存在");
        }
        Integer docId = user.getUserId();
        medicalTechnician1.setDocId(docId);
        return medicalTechnicianMapper.updateMedicalTechnician(medicalTechnician1);
    }

    /**
     * 删除医技信息
     * @param technicianId
     * @return
     */
    @Override
    public int deleteMedicalTechnicianById(Integer technicianId) {
        MedicalTechnician medicalTechnician = medicalTechnicianMapper.selectMedicalTechnicianByTechnicianId(technicianId);
        if (medicalTechnician == null){
            throw new ServiceException(Constants.CODE_600, "要删除的医技信息不存在");
        }
        return medicalTechnicianMapper.deleteMedicalTechnicianById(technicianId);
    }

    @Override
    public Map<String, Object> getMedicalTechnicianByPageAndTechnicianName(String technicianName, int pageNum, int pageSize) {
        //对页数进行处理
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int total = medicalTechnicianMapper.selectTotalByTechnicianName(technicianName);
        if (total == 0){
            throw new ServiceException(Constants.CODE_600, "未找到任何医技信息");
        }
        List<MedicalTechnician> medicalTechnicianList = medicalTechnicianMapper.selectMedicalTechnicianByPageAndTechnicianName(technicianName, pageNum, pageSize);
        List<MedicalTechnicianDTO> medicalTechnicianDTOList = new ArrayList<>();
        for (MedicalTechnician medicalTechnician : medicalTechnicianList){
            MedicalTechnicianDTO medicalTechnicianDTO = new MedicalTechnicianDTO();
            medicalTechnicianDTO.setTechnicianId(medicalTechnician.getTechnicianId());
            medicalTechnicianDTO.setTechnicianName(medicalTechnician.getTechnicianName());
            medicalTechnicianDTO.setCost(medicalTechnician.getCost());
            User user = userMapper.selectUserByUserId(medicalTechnician.getDocId());
            if (user == null){
                throw new ServiceException(Constants.CODE_600, "负责帐号不存在");
            }
            medicalTechnicianDTO.setUsername(user.getUsername());
            medicalTechnicianDTOList.add(medicalTechnicianDTO);
        }
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", medicalTechnicianDTOList);
        res.put("total", total);
        return res;
    }

    @Override
    public List<String> getAllTechnicianName() {
        List<String> technicianNameList = medicalTechnicianMapper.selectAllTechnicianName();
        if (technicianNameList.size() == 0){
            throw new ServiceException(Constants.CODE_600, "未找到任何医技信息");
        }
        return technicianNameList;
    }
}
