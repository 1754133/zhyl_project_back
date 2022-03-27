package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.mapper.WardInfoMapper;
import com.hust.wit120back.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.ConstantCallSite;
import java.util.List;

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
}
