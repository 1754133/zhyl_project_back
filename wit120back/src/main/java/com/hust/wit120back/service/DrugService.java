package com.hust.wit120back.service;

import com.hust.wit120back.entity.Drug;

import java.util.List;
import java.util.Map;

public interface DrugService {
    Map<String, Object> getDrugByPage(int pageNum, int pageSize);

    boolean addDrug(Drug drug);

    int updateDrug(Drug drug);

    int deleteDrug(Integer drugId);
}
