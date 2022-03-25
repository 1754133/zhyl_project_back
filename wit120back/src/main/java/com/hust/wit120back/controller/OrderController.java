package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.aliyun.tea.utils.TrueHostnameVerifier;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.config.interceptor.JwtInterceptor;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.entity.Order;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//OrderService
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
        //判断前端的参数
        try{
            orderService.checkParameter(orderDTO);
        }catch (ServiceException e){
            return Result.success(e);
        }
        //医生值班信息表中增加挂号人数
        orderService.addPatient(orderDTO.getDoctorId(), orderDTO.getOrderDay(), orderDTO.getOrderTimeSlice());
        //数据库中增加挂号单
        orderService.addAppointment(orderDTO);
        return Result.success();
    }

    @DeleteMapping("/appointment/{orderId}")
    public Result deleteAppointment(@PathVariable Integer orderId){
        orderService.deleteOrder(orderId);
        return Result.success();
    }
}
