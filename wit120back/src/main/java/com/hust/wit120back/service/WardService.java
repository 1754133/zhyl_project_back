package com.hust.wit120back.service;

import com.hust.wit120back.dto.WardInfoDTO;

import java.util.List;
import java.util.Map;

public interface WardService {
    List<Integer> getEmptyWard();
    List<Integer> getEmptyBedByWardId(Integer wardId);
    boolean addPatientWardInfo(WardInfoDTO wardInfoDTO);
    List<WardInfoDTO> getPatientInfo(Integer doctorId);
    boolean deleteWardInfo(Integer patientId);

    boolean addCheckRecord(Integer patientId, String checkRecord);
    String getCheckRecord(Integer patientId);

    Map<Integer, Integer> systemRecommendWard();
}
