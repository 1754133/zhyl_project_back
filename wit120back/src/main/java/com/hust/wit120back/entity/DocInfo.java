package com.hust.wit120back.entity;

import lombok.Data;

@Data
public class DocInfo {
    Integer docInfoId;
    Integer docId;
    String docName;
    Integer departmentId;
    String level;
    String docDesc;
    String identificationNum;
    boolean gender;
    int age;
}
