package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class PatientInfoDTO {
    private String username;
    private String realName;
    private String identificationNum;
    private boolean gender;
    private int age;
}
