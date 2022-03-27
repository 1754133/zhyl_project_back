package com.hust.wit120back.service;

import com.hust.wit120back.dto.WardInfoDTO;

import java.util.List;

public interface WardService {
    List<Integer> getEmptyWard();
    List<Integer> getEmptyBedByWardId(Integer wardId);
    boolean addPatientWardInfo(WardInfoDTO wardInfoDTO);

    boolean addCheckRecord(Integer patientId, String checkRecord);
    String getCheckRecord(Integer patientId);
}
