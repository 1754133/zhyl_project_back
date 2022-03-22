package com.hust.wit120back.service;

import com.hust.wit120back.dto.MedResOrderDTO;

import java.util.List;

public interface MedicalResourceService {
    void checkParameter(MedResOrderDTO medResOrderDTO);
    void addAppointment(MedResOrderDTO medResOrderDTO);
    List<MedResOrderDTO> getAllMedResAppointment(Integer patientId);
    List<MedResOrderDTO> getMedResAppointment(Integer patientId, Integer orderId);
}
