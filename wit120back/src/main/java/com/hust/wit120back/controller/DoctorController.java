package com.hust.wit120back.controller;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/conciseShiftInfo/{doctorId}")
    public Result getConciseShiftInfo(@PathVariable Integer doctorId){
        ArrayList<ConciseShiftInfoDTO> shiftInfos;
        try{
            shiftInfos = doctorService.getDocConciseShiftInfo(doctorId);
        }catch(ServiceException e){
            return Result.error(Constants.CODE_503, "无坐诊信息");
        }
        return Result.success(shiftInfos);
    }

    @GetMapping("/shiftInfo/{doctorId}")
    public Result getShiftInfo(@PathVariable Integer doctorId){
        ArrayList<ShiftInfoDTO> shiftInfos;
        try{
            shiftInfos = doctorService.getDocShiftInfo(doctorId);
        }catch(ServiceException e){
            return Result.error(Constants.CODE_503, "无坐诊信息");
        }
        return Result.success(shiftInfos);
    }
}
