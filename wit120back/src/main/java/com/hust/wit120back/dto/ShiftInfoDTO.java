package com.hust.wit120back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ShiftInfoDTO {
    private Integer doctorId;
    private String doctorName;
    private int orderDay;
    /**
     *  如果是返回科室所有医生坐诊信息：123表示上午时段，345表示下午时段
     *  如果是返回某位医生的坐诊信息，1表示上午，2表示下午
     */
    private int orderTimeSlice;
    private int patientsNumber;
}
