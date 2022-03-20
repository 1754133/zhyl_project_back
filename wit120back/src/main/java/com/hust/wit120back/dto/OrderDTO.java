package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class OrderDTO implements Comparable<OrderDTO>{
    private String username;
    private Integer doctorId;
    private String doctorName;
    private String departmentName;
    private int orderDay; //预约时间：周几
    private int orderTimeSlice; //预约时间段
    //orderTime: YYYY-MM-DD
    private String orderTime; //预约日期
    //createTime: YYYY-MM-DD HH:MM:SS
    private String createTime; //预约单生成时间
    private int cost;

    @Override
    public int compareTo(OrderDTO order){
        String date1 = this.orderTime;
        String date2 = order.getOrderTime();
        int year1 = Integer.parseInt(date1.substring(0, 4)), year2 = Integer.parseInt(date2.substring(0, 4));
        int month1 = Integer.parseInt(date1.substring(5, 7)), month2 = Integer.parseInt(date2.substring(5, 7));
        int day1 = Integer.parseInt(date1.substring(8, 10)), day2 = Integer.parseInt(date2.substring(8, 10));
        if(year1 != year2){
            return year1 - year2;
        }else if(month1 != month2){
            return month1 - month2;
        }
        return day1 - day2;
    }
}
