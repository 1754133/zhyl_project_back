package com.hust.wit120back.service;

import com.hust.wit120back.dto.ConciseShiftInfoDTO;

import java.util.ArrayList;

public interface DoctorService {
    ArrayList<ConciseShiftInfoDTO> getDocShiftInfo(Integer doctorId);
}
