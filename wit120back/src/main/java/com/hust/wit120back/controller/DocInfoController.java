package com.hust.wit120back.controller;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.DocInfoDTO;
import com.hust.wit120back.service.DocInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/docInfo")
public class DocInfoController {
    @Autowired
    private DocInfoService docInfoService;

    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable int pageNum, @PathVariable int pageSize){
        return Result.success(docInfoService.selectPage(pageNum, pageSize));
    }

    /**
     * 按名字分页模糊查询
     * @param
     * @return
     */
    @GetMapping("/page/{doctorName}/{pageNum}/{pageSize}")
    public Result findPageByName(@PathVariable String doctorName, @PathVariable int pageNum, @PathVariable int pageSize){
        return Result.success(docInfoService.selectPageByDoctorName(doctorName, pageNum, pageSize));
    }

    @PutMapping
    public Result updateDocInfo(@RequestBody DocInfoDTO docInfoDTO){
        if (docInfoDTO == null || docInfoDTO.getDocInfoId() == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(docInfoService.updateDocInfo(docInfoDTO));
    }

    @DeleteMapping("/{docInfoId}")
    public Result deleteDocInfo(@PathVariable Integer docInfoId){
        if (docInfoId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(docInfoService.deleteDocInfo(docInfoId));
    }

    @GetMapping("/{docId}")
    public Result getDocInfoByDocId(@PathVariable Integer docId){
        if (docId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(docInfoService.getDocInfoByDocId(docId));
    }
}
