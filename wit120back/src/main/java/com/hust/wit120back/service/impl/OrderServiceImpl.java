package com.hust.wit120back.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.entity.Order;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.OrderMapper;
import com.hust.wit120back.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void checkParameter(OrderDTO orderDTO){
        String username = orderDTO.getUsername();
        String doctorName = orderDTO.getDoctorName();
        int orderDay = orderDTO.getOrderDay();
        int orderTimeSlice = orderDTO.getOrderTimeSlice();
        int cost = orderDTO.getCost();
        //参数错误
        if(StrUtil.isBlank(username) || StrUtil.isBlank(doctorName))
            throw new ServiceException(Constants.CODE_400, "参数错误");
        if((orderDay < 1 || orderDay > 7) || (orderTimeSlice < 1 || orderTimeSlice > 6) || cost <= 0)
            throw new ServiceException(Constants.CODE_400, "参数错误");
        Integer patientId = orderMapper.selectPatientIdByName(username);
        Integer doctorId = orderMapper.selectDoctorIdByName(doctorName);
        //查询该时段该用户是否已经预约
        if(orderMapper.selectOrder(patientId, doctorId, orderDay, orderTimeSlice) != null)
            throw new ServiceException(Constants.CODE_502, "该用户已预约");
        //查询该时段名额是否已满
        Integer patientsNum = orderMapper.selectNumAppointmentByDoctorAndTime(doctorId, orderDay, orderTimeSlice);
        if(patientsNum == null || patientsNum >= 4)
            throw new ServiceException(Constants.CODE_501, "该时段挂号名额已满或该医生并未坐诊");
    }

    @Override
    public void addPatient(String username, String doctorName, int orderDay, int orderTimeSlice){
        Integer doctorId = orderMapper.selectDoctorIdByName(doctorName);
        orderMapper.addPatientsNumber(doctorId, orderDay, orderTimeSlice);
    }

    @Override
    public void addAppointment(OrderDTO orderDTO){
        Integer patientId = orderMapper.selectPatientIdByName(orderDTO.getUsername());
        Integer doctorId = orderMapper.selectDoctorIdByName(orderDTO.getDoctorName());
        Order order = new Order();
        order.setPatientId(patientId);
        order.setDoctorId(doctorId);
        order.setOrderDay(orderDTO.getOrderDay());
        order.setOrderTimeSlice(orderDTO.getOrderTimeSlice());
        order.setCost(orderDTO.getCost());
        orderMapper.addAppointment(order);
    }

}
