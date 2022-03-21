package com.hust.wit120back.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String t){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "";
        if (!"".equals(t)){
            //t不为空串
            try{
                //getTime() returns the number of milliseconds since January 1, 1970
                res = String.valueOf(sdf.parse(t).getTime() / 1000);
            }catch(Exception e) {
                System.out.println("传入了null值");
            }
        }else{
            //t为空串
            long time = System.currentTimeMillis();
            res = String.valueOf(time / 1000);
        }
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(int time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(new Date(time * 1000L));
        //System.out.println("日期格式---->" + times);
        return times;
    }

    /**
     * 计算日期
     */
    public static String calDate(String curDate, int k){
        // 默认k > 0，因为预约的日期总会等于或者晚于预约单创建的日期
        int year = Integer.parseInt(curDate.substring(0, 4));
        int month = Integer.parseInt(curDate.substring(5, 7));
        int day = Integer.parseInt(curDate.substring(8, 10));
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        //判断是否为闰年
        if((year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0))
            days[1] = 29;
        while(k-- > 0){
            day++;
            if(day > days[month - 1]) {
                day -= days[month - 1];
                month++;
                if (month > 12) {
                    month -= 12;
                    year++;
                }
            }
        }
        /*
        //若k < 0（备用）
        if(k < 0){
            while(k++ < 0){
                day--;
                if(day == 0){
                    month--;
                    if(month == 0){
                        month += 12;
                        year--;
                    }
                    day += days[month - 1];
                }
            }
        }
         */
        curDate = String.valueOf(year) + "-" + (month < 10 ? "0" : "") + String.valueOf(month) + "-" +
                 (day < 10 ? "0" : "") + String.valueOf(day);
        return curDate;
    }

    /**
     *
     */
    public static String getOrderDate(String createDate, int orderDay){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try{
            d = f.parse(createDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(d);
        int createDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(createDay == 0) createDay = 7;
        if(orderDay == 0) orderDay = 7;
        String orderDate = calDate(createDate, (orderDay + 7 - createDay) % 7);
        return orderDate;
    }
}
