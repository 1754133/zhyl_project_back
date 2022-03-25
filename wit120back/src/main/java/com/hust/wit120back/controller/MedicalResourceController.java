package com.hust.wit120back.controller;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.MedResOrderDTO;
import com.hust.wit120back.dto.MedicalTechnicianDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.service.MedicalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.ConstantCallSite;
import java.util.List;

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

    /**
     * 查询患者的所有医技预约单
     */
    @GetMapping("/appointment/{patientId}")
    public Result getMedResAppointment(@PathVariable Integer patientId){
        if(patientId == null)
            throw new ServiceException(Constants.CODE_400, "参数错误");
        List<MedResOrderDTO> medResOrders = medicalResourceService.getAllMedResAppointment(patientId);
        return Result.success(medResOrders);
    }

    /**
     * 查询患者的挂号单对应的医技预约单
     */
    @GetMapping("/appointment/order/{orderId}")
    public Result getMedResAppointmentByOrderId(@PathVariable Integer orderId){
        if(orderId == null)
            throw new ServiceException(Constants.CODE_400, "参数错误");
        List<MedResOrderDTO> medResOrders = medicalResourceService.getMedResAppointmentByOrderId(orderId);
        return Result.success(medResOrders);
    }

    /**
     * 保存医生推荐的医技预约
     */
    @PostMapping ("/recommend")
    public Result addMedResRecommend(@RequestParam Integer orderId, @RequestParam String recommend){
        if(orderId == null)
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(medicalResourceService.addMedResRecommend(orderId, recommend));
    }

    /**
     * 查询所有的医技资源，返回名称和对应id
     */
    @GetMapping
    public Result getMedResNameAndId(){
        return Result.success(medicalResourceService.getMedResNameAndId());
    }

    /**
     * 查询医技推荐
     */
    @GetMapping("/recommend/{orderId}")
    public Result getMedResRecommend(@PathVariable Integer orderId){
        if(orderId == null)
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(medicalResourceService.getMedResRecommend(orderId));
    }

    /**
     * 医技医生根据日期和自己的id查询某日的医技预约
     */
    @GetMapping("/orders/{doctorId}/{date}")
    public Result getMedResOrderByIdAndDate(@PathVariable Integer doctorId, @PathVariable String date){
        //date: YYYY-MM-DD
        if(doctorId == null)
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(medicalResourceService.getMedResOrderByIdAndDate(doctorId, date));
    }

    /**
     * 查询医技预约检查结果
     * 参数：医技预约单的id
     */
    @GetMapping("/checkResult/{medResOrderId}")
    public Result getCheckResult(@PathVariable Integer medResOrderId){
        if(medResOrderId == null)
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(medicalResourceService.getCheckResult(medResOrderId));
    }

    /**
     * 增加医技检查结果
     * 传入参数：医技预约单id，检查结果
     */
    @PostMapping("/checkResult")
    public Result addCheckResult(@RequestParam Integer medResOrderId, @RequestParam String checkResult){
        if(medResOrderId == null)
            return Result.error(Constants.CODE_600, "参数错误");
        return Result.success(medicalResourceService.addCheckResult(medResOrderId, checkResult));
    }
}
