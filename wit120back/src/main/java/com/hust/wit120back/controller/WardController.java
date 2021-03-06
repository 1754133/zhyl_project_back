package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.WardInfoDTO;
import com.hust.wit120back.mapper.WardInfoMapper;
import com.hust.wit120back.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.ConstantCallSite;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ward")
public class WardController {
    @Autowired
    private WardService wardService;

    @GetMapping("/emptyWard")
    public Result getEmptyWard(){
        return Result.success(wardService.getEmptyWard());
    }

    @GetMapping("/emptyWard/{wardId}")
    public Result getEmptyBedByWardId(@PathVariable Integer wardId){
        return Result.success(wardService.getEmptyBedByWardId(wardId));
    }

    /**
     * 查看医生所负责的病人的住院记录
     */
    @GetMapping("/patientInfo/{doctorId}")
    public Result getPatientInfo(@PathVariable Integer doctorId){
        if(doctorId == null)
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(wardService.getPatientInfo(doctorId));
    }

    /**
     * 增加住院记录
     */
    @PostMapping("/wardInfo")
    public Result addPatientWardInfo(@RequestBody WardInfoDTO wardInfoDTO){
        if(wardInfoDTO.getWardId() == null || wardInfoDTO.getPatientId() == null || wardInfoDTO.getBedId() == null ||
        wardInfoDTO.getPrincipalId() == null)
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(wardService.addPatientWardInfo(wardInfoDTO));
    }

    /**
     * 增加查房记录，如果已有记录则进行更新
     */
    @PostMapping("/checkRecord")
    public Result addCheckRecord(@RequestParam Integer patientId, @RequestParam String checkRecord){
        if(patientId == null || StrUtil.isBlank(checkRecord))
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(wardService.addCheckRecord(patientId, checkRecord));
    }

    /**
     * 查询查房记录
     */
    @GetMapping("/checkRecord/{patientId}")
    public Result getCheckRecord(@PathVariable Integer patientId){
        if(patientId == null)
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(wardService.getCheckRecord(patientId));
    }

    /**
     * 病人出院，删除其住院信息、查房记录
     */
    @DeleteMapping("/wardInfo/{patientId}")
    public Result deleteWardInfo(@PathVariable Integer patientId){
        if(patientId == null)
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(wardService.deleteWardInfo(patientId));
    }

    /**
     * 系统推荐入院，返回推荐的病房号和病床号
     */
    @GetMapping("/systemRecommend")
    public Result systemRecommendWard(){
        Map<String, Integer> recommend = wardService.systemRecommendWard();
        if(recommend.isEmpty())
            return Result.error(Constants.CODE_600, "无剩余床位");
        return Result.success(recommend);
    }

}
