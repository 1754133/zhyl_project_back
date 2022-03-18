package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class ConciseShiftInfoDTO {
    private Integer doctorId;
    private int day;
    //1 represents morning, 2 represents afternoon
    private int noon;
}
