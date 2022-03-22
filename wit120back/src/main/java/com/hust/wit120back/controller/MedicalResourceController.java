package com.hust.wit120back.controller;

import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.MedResOrderDTO;
import com.hust.wit120back.dto.MedicalTechnicianDTO;
import com.hust.wit120back.service.MedicalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalResource")
public class MedicalResourceController {
    @Autowired
    private MedicalResourceService medicalResourceService;

    /**
     * 医技预约
     */
    @PostMapping("/appointment")
    public Result appointment(@RequestBody MedResOrderDTO medResOrderDTO){
        //判断参数
        try{
            medicalResourceService.checkParameter(medResOrderDTO);
        }catch (Exception e){
            return Result.success(e);
        }
        //预约单增加预约记录
        medicalResourceService.addAppointment(medResOrderDTO);
        return Result.success();
    }
}
