package com.hust.wit120back.service;

import com.hust.wit120back.dto.MedResOrderDTO;

import java.util.List;
import java.util.Map;

public interface MedicalResourceService {
    void checkParameter(MedResOrderDTO medResOrderDTO);
    void addAppointment(MedResOrderDTO medResOrderDTO);
    List<MedResOrderDTO> getAllMedResAppointment(Integer patientId);
    List<MedResOrderDTO> getMedResAppointmentByOrderId(Integer orderId);
    boolean addMedResRecommend(Integer orderId, String recommend);
    List<Map<String, Integer>> getMedResNameAndId();
    String getMedResRecommend(Integer orderId);
    List<MedResOrderDTO> getMedResOrderByIdAndDate(Integer doctorId, String date);
}
