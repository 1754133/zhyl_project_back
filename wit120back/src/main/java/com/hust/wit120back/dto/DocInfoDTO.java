package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class DocInfoDTO {
    Integer docInfoId;
    String docName;
    String department;
    String level;
    String docDesc;
    String identificationNum;
    boolean gender;
    int age;
}
