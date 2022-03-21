package com.hust.wit120back.service;

import com.hust.wit120back.dto.MedicalTechnicianDTO;

import java.util.List;
import java.util.Map;

public interface MedicalTechnicianService {
    //分页查询医技条目
    Map<String, Object> getMedicalTechnicianByPage(int pageNum, int pageSize);

    boolean addMedicalTechnician(MedicalTechnicianDTO medicalTechnicianDTO);

    int updateMedicalTechnician(MedicalTechnicianDTO medicalTechnicianDTO);

    int deleteMedicalTechnicianById(Integer technicianId);
}
