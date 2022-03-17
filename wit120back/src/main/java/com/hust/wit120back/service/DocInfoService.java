package com.hust.wit120back.service;

import com.hust.wit120back.dto.DocInfoDTO;
import com.hust.wit120back.entity.DocInfo;

import java.util.Map;

public interface DocInfoService {
    Map<String, Object> selectPage(int pageNum, int pageSize);

    int updateDocInfo(DocInfoDTO docInfoDTO);

    int deleteDocInfo(Integer docInfoId);
}
