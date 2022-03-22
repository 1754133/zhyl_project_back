package com.hust.wit120back.service;

import com.hust.wit120back.dto.MedResOrderDTO;

public interface MedicalResourceService {
    void checkParameter(MedResOrderDTO medResOrderDTO);
    void addAppointment(MedResOrderDTO medResOrderDTO);
}
