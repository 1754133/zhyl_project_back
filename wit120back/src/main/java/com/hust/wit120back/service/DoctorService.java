package com.hust.wit120back.service;

import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;

import java.util.ArrayList;
import java.util.List;

public interface DoctorService {
    ArrayList<ConciseShiftInfoDTO> getDocConciseShiftInfo(Integer doctorId);
    ArrayList<ShiftInfoDTO> getDocShiftInfo(Integer doctorId);
    List<ShiftInfoDTO> getDocShiftInfoBySlice(Integer doctorId, int orderDay, int noon);
    List<OrderDTO> getPatientsByDate(Integer doctorId, String date);
    boolean addPrescription(Integer orderId, String prescription);
    String getPrescription(Integer orderId);
    int updatePrescription(Integer orderId, String prescription);
    boolean addCaseHistory(Integer orderId, String prescription);
    String getCaseHistory(Integer orderId);
    int updateCaseHistory(Integer orderId, String caseHistory);
}
