package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class OrderDTO {
    String username;
    String doctorName;
    int orderDay;
    int orderTimeSlice;
    int cost;
}
