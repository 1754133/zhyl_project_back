package com.hust.wit120back.entity;

import lombok.Data;

@Data
public class Drug {
    Integer drugId;
    String drugName;
    //批准文号
    String approvalNum;
    //剂型
    String formulation;
    //规格
    String specification;
    //生产厂商
    String manufacturer;
    //分类
    String category;
    //单价
    double cost;
}
