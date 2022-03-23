package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class MedResOrderDTO implements Comparable<MedResOrderDTO>{
    private Integer medResOrderId;
    private Integer orderId;
    private Integer patientId;
    private String patientName;
    private Integer medResId;
    private String medResName;
    private String createTime;
    private String orderTime; //预约日期
    //day: 1 ~ 6表示周一到周六，0表示周日
    private int day;
    //noon: 1表示上午，2表示下午
    private int noon;
    private int cost;
    private boolean deal;
    /*
    进行医技预约的时候：前端需要传的参数：patientId, orderId, medResName（医技资源名称）, day, noon
    医技预约查询：
        前端参数：patientId, orderId
        返回参数：medResOrderId（医技预约单编号）, medResName（医技资源名称）, createTime（预约单生成的时间）,day, noon, cost
     */

    @Override
    public int compareTo(MedResOrderDTO medResOrder){
        System.out.println("hhhhhhhhhhhhhhh");
        String date1 = this.orderTime;
        String date2 = medResOrder.getOrderTime();
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
