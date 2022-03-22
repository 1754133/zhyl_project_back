package com.hust.wit120back.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.MedResOrderDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.*;
import com.hust.wit120back.service.MedicalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalResourceServiceImpl implements MedicalResourceService {
    @Autowired
    private PatientInfoMapper patientInfoMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MedicalResourceMapper medicalResourceMapper;

    @Autowired
    private MedicalResourceOrderMapper medicalResourceOrderMapper;

    @Override
    public void checkParameter(MedResOrderDTO medResOrderDTO){
        //check patientId, orderId, medResName, day, noon, cost
        Integer patientId = medResOrderDTO.getPatientId();
        Integer orderId = medResOrderDTO.getOrderId();
        String medResName = medResOrderDTO.getMedResName();
        Integer medResId = medicalResourceMapper.selectMedResName(medResName);
        int day = medResOrderDTO.getDay();
        int noon = medResOrderDTO.getNoon();
        int cost = medResOrderDTO.getCost();
        if(patientInfoMapper.selectPatientId(patientId) == null)
            throw new ServiceException(Constants.CODE_600, "该患者信息不存在");
        else if(orderMapper.selectOrderId(orderId) == null)
            throw new ServiceException(Constants.CODE_600, "挂号信息不存在");
        else if(StrUtil.isBlank(medResName) || medResId == null)
            throw new ServiceException(Constants.CODE_400, "设备名错误");
        else if(day < 0 || day > 6 || !(noon == 1 || noon == 2))
            throw new ServiceException(Constants.CODE_400, "预约日期错误");
        //查询是否已经预约
        if(medicalResourceOrderMapper.selectMedResOrderId(orderId, medResId) != null)
            throw new ServiceException(Constants.CODE_502, "该用户已预约");
        //是否还有预约名额
        if(medicalResourceOrderMapper.selectOrderNumber(day, noon, medResId) >= 12)
            throw new ServiceException(Constants.CODE_501, "该时段已无预约名额");
    }

    @Override
    public void addAppointment(MedResOrderDTO medResOrderDTO){
        int cost = medicalResourceMapper.selectMedResCost(medResOrderDTO.getMedResName());
        medResOrderDTO.setCost(cost);
        Integer medResId = medicalResourceMapper.selectMedResName(medResOrderDTO.getMedResName());
        medResOrderDTO.setMedResId(medResId);
        medicalResourceOrderMapper.addAppointment(medResOrderDTO);
    }
}
