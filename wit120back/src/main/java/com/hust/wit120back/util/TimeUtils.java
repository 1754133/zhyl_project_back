package com.hust.wit120back.util;

import java.text.SimpleDateFormat;
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
}
