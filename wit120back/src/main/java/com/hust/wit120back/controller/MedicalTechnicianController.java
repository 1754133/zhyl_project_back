package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.MedicalTechnicianDTO;
import com.hust.wit120back.service.MedicalTechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalTechnician")
public class MedicalTechnicianController {
    @Autowired
    private MedicalTechnicianService medicalTechnicianService;

    /**
     * 分页查询医技信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result getMedicalTechnicianByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        return Result.success(medicalTechnicianService.getMedicalTechnicianByPage(pageNum, pageSize));
    }

    /**
     * 新增医技信息
     * @param medicalTechnicianDTO
     * @return
     */
    @PostMapping
    public Result addMedicalTechnician(@RequestBody MedicalTechnicianDTO medicalTechnicianDTO){
        String technicianName = medicalTechnicianDTO.getTechnicianName();
        String username = medicalTechnicianDTO.getUsername();
        int cost = medicalTechnicianDTO.getCost();
        //参数检测
        if (cost <= 0 || StrUtil.isBlank(technicianName) || StrUtil.isBlank(username)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(medicalTechnicianService.addMedicalTechnician(medicalTechnicianDTO));
    }

    /**
     * 修改医技信息
     * @param medicalTechnicianDTO
     * @return
     */
    @PutMapping
    public Result updateMedicalTechnician(@RequestBody MedicalTechnicianDTO medicalTechnicianDTO){
        Integer technicianId= medicalTechnicianDTO.getTechnicianId();
        String technicianName = medicalTechnicianDTO.getTechnicianName();
        String username = medicalTechnicianDTO.getUsername();
        int cost = medicalTechnicianDTO.getCost();
        //参数检测
        if (cost <= 0 || StrUtil.isBlank(technicianName) || StrUtil.isBlank(username) || technicianId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(medicalTechnicianService.updateMedicalTechnician(medicalTechnicianDTO));
    }

    /**
     * 删除医技信息
     * @param technicianId
     * @return
     */
    @DeleteMapping("/{technicianId}")
    public Result deleteMedicalTechnician(@PathVariable Integer technicianId){
        if (technicianId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(medicalTechnicianService.deleteMedicalTechnicianById(technicianId));
    }
}
