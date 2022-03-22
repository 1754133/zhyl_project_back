package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class MedResOrderDTO {
    private Integer medResOrderId;
    private Integer orderId;
    private Integer patientId;
    private Integer medResId;
    private String medResName;
    private String createTime;
    //day: 1 ~ 6表示周一到周六，0表示周日
    private int day;
    //noon: 1表示上午，2表示下午
    private int noon;
    private int cost;

    /*
    进行医技预约的时候，前端需要传的参数：patientId, orderId, medResName, day, noon
     */
}
