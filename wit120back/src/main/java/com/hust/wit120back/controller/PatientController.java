package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.PatientInfoDTO;
import com.hust.wit120back.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PutMapping("/patientInfo")
    public Result updatePatientInfo(@RequestBody PatientInfoDTO patientInfoDTO){
        if (StrUtil.isBlank(patientInfoDTO.getUsername())){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(patientService.updatePatientInfo(patientInfoDTO));
    }

    @GetMapping("/patientInfo/{username}")
    public Result getPatientInfo(@PathVariable String username){
        if (StrUtil.isBlank(username)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(patientService.getPatientInfo(username));
    }
}
