package com.hust.wit120back.service;

import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;

import java.util.ArrayList;

public interface DoctorService {
    ArrayList<ConciseShiftInfoDTO> getDocConciseShiftInfo(Integer doctorId);
    ArrayList<ShiftInfoDTO> getDocShiftInfo(Integer doctorId);
}
