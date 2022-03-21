package com.hust.wit120back.controller;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/shiftInfo/slice/{doctorId}/{orderDay}/{noon}")
    public Result getPatientNumBySlice(@PathVariable Integer doctorId, @PathVariable int orderDay, @PathVariable int noon){
        if (doctorId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(doctorService.getDocShiftInfoBySlice(doctorId, orderDay, noon));
    }

    @GetMapping("/todayPatient/{doctorId}/{date}")
    public Result getPatientsByDate(@PathVariable Integer doctorId, @PathVariable String date){
        //date YYYY-MM-DD
        if(doctorId == null)
            return Result.error(Constants.CODE_400, "参数错误");
        List<OrderDTO> orders = doctorService.getPatientsByDate(doctorId, date);
        if(orders.size() == 0)
            return Result.error(Constants.CODE_600, "无挂号患者信息");
        return Result.success(orders);
    }
}
