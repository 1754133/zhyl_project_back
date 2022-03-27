package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.WardInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.CheckRecordMapper;
import com.hust.wit120back.mapper.PatientInfoMapper;
import com.hust.wit120back.mapper.WardInfoMapper;
import com.hust.wit120back.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("WardService")
public class WardServiceImpl implements WardService {
    @Autowired
    private WardInfoMapper wardInfoMapper;

    @Autowired
    private CheckRecordMapper checkRecordMapper;


    @Override
    public List<Integer> getEmptyWard(){
        List<Integer> emptyWard = new ArrayList<Integer>();
        //定义楼层
        int floor = 2;
        //x01 ~ x20
        for(int i = 1; i <= floor; i++){
            for(int id = i * 100 + 1; id <= i * 100 + 20; id++){
                int num = wardInfoMapper.selectWardNumByWardId(id);
                if(num >= 0 && num < 4)
                    emptyWard.add(id);
            }
        }
        return emptyWard;
    }

    @Override
    public List<Integer> getEmptyBedByWardId(Integer wardId){
        List<Integer> emptyBed = new ArrayList<Integer>();
        //每个房间有四张床位
        for(int i = 1; i <= 4; i++){
            Integer bedId = wardInfoMapper.selectBedByWardAndBedId(wardId, i);
            if(bedId == null)
                emptyBed.add(i);
        }
        return emptyBed;
    }

    @Override
    public boolean addPatientWardInfo(WardInfoDTO wardInfoDTO){
        //默认前端传入的参数无误
        Integer wardId = wardInfoDTO.getWardId();
        Integer bedId = wardInfoDTO.getBedId();
        Integer patientId = wardInfoDTO.getPatientId();
        Integer principalId = wardInfoDTO.getPrincipalId();
        wardInfoMapper.addPatientWardInfo(wardId, bedId, patientId, principalId);
        return true;
    }

    @Override
    public boolean addCheckRecord(Integer patientId, String checkRecord){
        if(checkRecordMapper.selectPatientIdByItself(patientId) == null)
            checkRecordMapper.addCheckResult(patientId, checkRecord);
        else
            checkRecordMapper.updateCheckResult(patientId, checkRecord);
        return true;
    }

    @Override
    public String getCheckRecord(Integer patientId){
        if(checkRecordMapper.selectPatientIdByItself(patientId) == null)
            throw new ServiceException(Constants.CODE_600, "该病人无住院记录");
        return checkRecordMapper.selectCheckRecordByPatientId(patientId);
    }
}
