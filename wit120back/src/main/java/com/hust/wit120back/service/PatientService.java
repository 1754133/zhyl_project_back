package com.hust.wit120back.service;

import com.hust.wit120back.dto.PatientInfoDTO;
import com.hust.wit120back.entity.PatientInfo;

public interface PatientService {
    int updatePatientInfo(PatientInfoDTO patientInfoDTO);

    PatientInfo getPatientInfo(String username);

    String getRealNameByUsername(String username);
}
