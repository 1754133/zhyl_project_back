package com.hust.wit120back.service;

import java.util.List;

public interface WardService {
    List<Integer> getEmptyWard();
    List<Integer> getEmptyBedByWardId(Integer wardId);
    boolean addCheckRecord(Integer patientId, String checkRecord);
    String getCheckRecord(Integer patientId);
}
