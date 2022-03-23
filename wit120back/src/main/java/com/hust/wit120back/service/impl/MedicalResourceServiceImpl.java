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

import java.util.List;
import java.util.Map;

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

    @Autowired
    private ResourceRecommendMapper resourceRecommendMapper;

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

    @Override
    public List<MedResOrderDTO> getAllMedResAppointment(Integer patientId){
        List<MedResOrderDTO> medResOrders;
        medResOrders = medicalResourceOrderMapper.selectAllMedResOrdersByPatientId(patientId);
        for(MedResOrderDTO medResOrder : medResOrders){
            medResOrder.setMedResName(medicalResourceMapper.selectMedResNameById(medResOrder.getMedResId()));
        }
        return medResOrders;
    }

    @Override
    public List<MedResOrderDTO> getMedResAppointment(Integer patientId, Integer orderId){
        List<MedResOrderDTO> medResOrders;
        medResOrders = medicalResourceOrderMapper.selectMedResOrderByPatientAndOrderId(patientId, orderId);
        for(MedResOrderDTO medResOrder : medResOrders){
            medResOrder.setMedResName(medicalResourceMapper.selectMedResNameById(medResOrder.getMedResId()));
        }
        return medResOrders;
    }

    @Override
    public boolean addMedResRecommend(Integer orderId, String recommend){
        if(resourceRecommendMapper.selectOrderId(orderId) == null){
            resourceRecommendMapper.addRecommend(orderId, recommend);
        }else{
            resourceRecommendMapper.updateRecommend(orderId, recommend);
        }
        return true;
    }

    @Override
    public List<Map<String, Integer>> getMedResNameAndId(){
        List<Map<String, Integer>> medRes = medicalResourceMapper.selectMedResNameAndId();
        return medRes;
    }
}
