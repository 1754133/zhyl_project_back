package com.hust.wit120back.service;

import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;

import java.util.ArrayList;
import java.util.List;

public interface DoctorService {
    ArrayList<ConciseShiftInfoDTO> getDocConciseShiftInfo(Integer doctorId);
    ArrayList<ShiftInfoDTO> getDocShiftInfo(Integer doctorId);

    List<ShiftInfoDTO> getDocShiftInfoBySlice(Integer doctorId, int orderDay, int noon);
}
