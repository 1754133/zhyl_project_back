package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.aliyun.tea.utils.TrueHostnameVerifier;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.config.interceptor.JwtInterceptor;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.entity.Order;
import com.hust.wit120back.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/test")
    public void test(){
        System.out.println("testing");
    }


    @PostMapping("/appointment")
    public Result appointment(@RequestBody OrderDTO orderDTO) throws Exception {
        String username = orderDTO.getUsername();
        String doctorName = orderDTO.getDoctorName();
        int orderDay = orderDTO.getOrderDay();
        int orderTimeSlice = orderDTO.getOrderTimeSlice();
        int cost = orderDTO.getCost();
        System.out.println("username: " + username);
        System.out.println("doctorname: " + doctorName);
        System.out.println("orderday: " + orderDay);
        System.out.println("ordertimeslice: " + orderTimeSlice);
        System.out.println("cost: " + cost);
        if(StrUtil.isBlank(username) || StrUtil.isBlank(doctorName)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        if((orderDay < 1 || orderDay > 7) || (orderTimeSlice < 1 || orderTimeSlice > 6) || cost <= 0){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        //查询该时段该用户是否已经预约
        if(orderService.hasMakeAppointment(orderDTO.getUsername(), orderDTO.getDoctorName(), orderDTO.getOrderDay(), orderDTO.getOrderTimeSlice())){
            return Result.error(Constants.CODE_502, "该用户已预约");
        }
        //查询该时段名额是否已满
        if(!orderService.isAppointmentLeft(doctorName, orderDay, orderTimeSlice)){
            //名额已满
            return Result.error(Constants.CODE_501, "该时段挂号名额已满或该医生并未坐诊");
        }
        //医生值班信息表中增加挂号人数
        orderService.addPatient(username, doctorName, orderDay, orderTimeSlice);
        //数据库中增加挂号单
        orderService.addAppointment(orderDTO);
        return Result.success();
    }
}
