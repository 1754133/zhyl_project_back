package com.hust.wit120back.entity;

import lombok.Data;

@Data
public class PatientInfo {
    Integer patientInfoId;
    Integer userId;
    String realName;
    String identificationNum;
    boolean gender;
    int age;
}
