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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("WardService")
public class WardServiceImpl implements WardService {
    @Autowired
    private WardInfoMapper wardInfoMapper;

    @Autowired
    private CheckRecordMapper checkRecordMapper;

    @Autowired
    private PatientInfoMapper patientInfoMapper;


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
        //判断数据库中是否已存在该住院信息
        if (wardInfoMapper.selectWardInfoByPatientId(wardInfoDTO.getPatientId()) != null){
            throw new ServiceException(Constants.CODE_700, "该患者已经住院");
        }
        //默认前端传入的参数无误
        Integer wardId = wardInfoDTO.getWardId();
        Integer bedId = wardInfoDTO.getBedId();
        Integer patientId = wardInfoDTO.getPatientId();
        Integer principalId = wardInfoDTO.getPrincipalId();
        wardInfoMapper.addPatientWardInfo(wardId, bedId, patientId, principalId);
        return true;
    }

    @Override
    public List<WardInfoDTO> getPatientInfo(Integer doctorId){
        List<WardInfoDTO> patientInfos = wardInfoMapper.selectPatientWardInfoByDoctorId(doctorId);
        for(WardInfoDTO patientInfo : patientInfos){
            //设置病人姓名、性别、年龄
            patientInfo.setPatientName(patientInfoMapper.selectRealNameById(patientInfo.getPatientId()));
            patientInfo.setGender(patientInfoMapper.selectGenderById(patientInfo.getPatientId()));
            patientInfo.setAge(patientInfoMapper.selectAgeByPatientId(patientInfo.getPatientId()));
        }
        return patientInfos;
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

    @Override
    public boolean deleteWardInfo(Integer patientId){
        return wardInfoMapper.deleteWardInfoByPatientId(patientId) && checkRecordMapper.deleteCheckRecordByPatientId(patientId);
    }

    @Override
    public Map<String, Integer> systemRecommendWard(){
        int floor = 2; //楼层为2
        Map<String, Integer> recommend = new HashMap<>();
        for(int i = 1; i <= floor; i++){
            for(int id = i * 100 + 1; id <= i * 100 + 20; id++){
                int num = wardInfoMapper.selectWardNumByWardId(id);
                if(num >= 0 && num < 4){
                    //有空床
                    for(int bed = 1; bed <= 4; bed++){
                        if(wardInfoMapper.selectBedByWardAndBedId(id, bed) == null){
                            recommend.put("wardId", id);
                            recommend.put("bedId", bed);
                            return recommend;
                        }
                    }
                }
            }
        }
        return recommend;
    }
}
