package com.hust.wit120back.entity;

import lombok.Data;

@Data
public class Order {
    Integer orderId;
    Integer patientId;
    Integer doctorId;
    String createTime;
    /**
     * orderDay: 1 -> Monday, 2 -> Tuesday, 3 -> Wednesday ...
     * orderTimeSlice: 1 -> 8:00-9:00, 2 -> 9:00-10:00, 3 -> 10:00-11:00
     *                  4 -> 14:00-15:00, 5 -> 15:00-16:00, 6 -> 16:00-17:00
     */
    int orderDay;
    int orderTimeSlice;
    int cost;
}
