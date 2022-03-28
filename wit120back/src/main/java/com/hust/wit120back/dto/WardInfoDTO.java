package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class WardInfoDTO {
    private Integer wardInfoId;
    private Integer wardId;
    private Integer bedId;
    private Integer patientId;
    private String patientName;
    private boolean gender; //true:男，false:女
    private int age;
    private Integer principalId; //医生时间
    private String createTime; //入院时间
}

/**
 * 根据医生id查询所负责的病人的住院信息
 * 前端传入：doctorId
 * 后端返回且前端需要显示：wardId（病房号），bedId（床位号），patientName，gender，age，createTime
 */
