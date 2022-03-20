package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.DoctorMapper;
import com.hust.wit120back.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("DoctorService")
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public ArrayList<ConciseShiftInfoDTO> getDocConciseShiftInfo(Integer doctorId){
        ArrayList<ConciseShiftInfoDTO> shiftInfos = doctorMapper.selectDocConciseShiftInfoById(doctorId);
        if(shiftInfos.size() == 0)
            throw new ServiceException(Constants.CODE_503, "无坐诊信息");
        return shiftInfos;
    }

    @Override
    public ArrayList<ShiftInfoDTO> getDocShiftInfo(Integer doctorId){
        ArrayList<ShiftInfoDTO> shiftInfos = doctorMapper.selectDocShiftInfoById(doctorId);
        if(shiftInfos.size() == 0)
            throw new ServiceException(Constants.CODE_503, "无坐诊信息");
        return shiftInfos;
    }

}
