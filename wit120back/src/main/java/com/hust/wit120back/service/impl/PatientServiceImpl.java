package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.PatientInfoDTO;
import com.hust.wit120back.entity.PatientInfo;
import com.hust.wit120back.entity.User;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.PatientInfoMapper;
import com.hust.wit120back.mapper.UserMapper;
import com.hust.wit120back.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientInfoMapper patientInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int updatePatientInfo(PatientInfoDTO patientInfoDTO) {
        User user = userMapper.selectUserByUsername(patientInfoDTO.getUsername());
        if (user == null){
            throw new ServiceException(Constants.CODE_700, "用户不存在");
        }
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setUserId(user.getUserId());
        patientInfo.setRealName(patientInfoDTO.getRealName());
        patientInfo.setIdentificationNum(patientInfoDTO.getIdentificationNum());
        patientInfo.setGender(patientInfoDTO.isGender());
        patientInfo.setAge(patientInfoDTO.getAge());
        return patientInfoMapper.updatePatientInfo(patientInfo);
    }

    @Override
    public PatientInfo getPatientInfo(String username) {
        User user = userMapper.selectUserByUsername(username);
        if (user == null){
            throw new ServiceException(Constants.CODE_400, "用户不存在");
        }
        return patientInfoMapper.selectPatientInfoByUserId(user.getUserId());
    }
}
