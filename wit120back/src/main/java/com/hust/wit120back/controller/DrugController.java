package com.hust.wit120back.controller;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.entity.Drug;
import com.hust.wit120back.service.DrugService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result getDrugByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        return Result.success(drugService.getDrugByPage(pageNum, pageSize));
    }

    /**
     * 根据药品名模糊查询
     * @param drugName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{drugName}/{pageNum}/{pageSize}")
    public Result getDrugByPageAndDrugName(@PathVariable String drugName, @PathVariable int pageNum, @PathVariable int pageSize){
        if (drugName == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(drugService.getDrugByPageAndDrugName(drugName, pageNum, pageSize));
    }

    @PostMapping
    public Result addDrug(@RequestBody Drug drug){
        if (drug.getDrugName() == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(drugService.addDrug(drug));
    }

    @PutMapping
    public Result updateDrug(@RequestBody Drug drug){
        if (drug.getDrugName() == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(drugService.updateDrug(drug));
    }

    @DeleteMapping("/{drugId}")
    public Result deleteDrug(@PathVariable Integer drugId){
        if (drugId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(drugService.deleteDrug(drugId));
    }
}
