package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.entity.Drug;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.DrugMapper;
import com.hust.wit120back.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugMapper drugMapper;

    @Override
    public Map<String, Object> getDrugByPage(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int total = drugMapper.selectTotal();
        if (total == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何药品信息");
        }
        List<Drug> drugList = drugMapper.selectDrugByPage(pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", drugList);
        res.put("total", total);
        return res;
    }

    @Override
    public boolean addDrug(Drug drug) {
        //检查数据库是否有相同的药品（药品名和生产厂家都相同才算）
        if (drugMapper.selectDrugByDrugNameAndManufacturer(drug.getDrugName(), drug.getManufacturer()) != null){
            throw new ServiceException(Constants.CODE_700, "药品已经存在");
        }
        drugMapper.addDrug(drug);
        return true;
    }

    @Override
    public int updateDrug(Drug drug) {
        if (drugMapper.selectDrugByDrugId(drug.getDrugId()) == null){
            throw new ServiceException(Constants.CODE_600, "要修改的药品信息不存在");
        }
        return drugMapper.updateDrug(drug);
    }

    @Override
    public int deleteDrug(Integer drugId) {
        if (drugMapper.selectDrugByDrugId(drugId) == null){
            throw new ServiceException(Constants.CODE_600, "要删除的药品信息不存在");
        }
        return drugMapper.deleteDrugByDrugId(drugId);
    }

    /**
     * 根据药品名模糊查询
     * @param drugName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getDrugByPageAndDrugName(String drugName, int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int total = drugMapper.selectTotalByDrugName(drugName);
        if (total == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何药品信息");
        }
        List<Drug> drugList = drugMapper.selectDrugByPageAndDrugName(drugName, pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", drugList);
        res.put("total", total);
        return res;
    }

    @Override
    public List<String> getAllDrugNames() {
        List<String> drugNameList = drugMapper.selectAllDrugNames();
        if (drugNameList.size() == 0){
            throw new ServiceException(Constants.CODE_600, "未找到任何药品");
        }
        return drugNameList;
    }
}
