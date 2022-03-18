package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private String username;
    private String doctorName;
    private int orderDay;
    private int orderTimeSlice;
    private int cost;
}
