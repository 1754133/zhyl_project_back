package com.hust.wit120back.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.entity.Order;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.DoctorShiftInfoMapper;
import com.hust.wit120back.mapper.OrderMapper;
import com.hust.wit120back.mapper.UserMapper;
import com.hust.wit120back.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DoctorShiftInfoMapper doctorShiftInfoMapper;

    @Override
    public void checkParameter(OrderDTO orderDTO){
        String username = orderDTO.getPatientName();
        Integer doctorId = orderDTO.getDoctorId();
        int orderDay = orderDTO.getOrderDay();
        int orderTimeSlice = orderDTO.getOrderTimeSlice();
        int cost = orderDTO.getCost();
        //参数错误
        if(StrUtil.isBlank(username) || doctorId == null)
            throw new ServiceException(Constants.CODE_400, "参数错误");
        if((orderDay < 0 || orderDay > 6) || (orderTimeSlice < 1 || orderTimeSlice > 6) || cost <= 0)
            throw new ServiceException(Constants.CODE_400, "参数错误");
        Integer patientId = userMapper.selectPatientIdByName(username);
        //查询该时段该用户是否已经预约
        if(orderMapper.selectOrder(patientId, doctorId, orderDay, orderTimeSlice) != null)
            throw new ServiceException(Constants.CODE_502, "该用户已预约");
        //查询该时段名额是否已满
        Integer patientsNum = doctorShiftInfoMapper.selectNumAppointmentByDoctorAndTime(doctorId, orderDay, orderTimeSlice);
        if(patientsNum == null || patientsNum >= 4)
            throw new ServiceException(Constants.CODE_501, "该时段挂号名额已满或该医生并未坐诊");
    }

    @Override
    public void addPatient(Integer doctorId, int orderDay, int orderTimeSlice){
        //Integer doctorId = orderMapper.selectDoctorIdByName(doctorName);
        doctorShiftInfoMapper.addPatientsNumber(doctorId, orderDay, orderTimeSlice);
    }

    @Override
    public void addAppointment(OrderDTO orderDTO){
        Integer patientId = userMapper.selectPatientIdByName(orderDTO.getPatientName());
        Integer doctorId = orderDTO.getDoctorId();
        Order order = new Order();
        order.setPatientId(patientId);
        order.setDoctorId(doctorId);
        order.setOrderDay(orderDTO.getOrderDay());
        order.setOrderTimeSlice(orderDTO.getOrderTimeSlice());
        order.setCost(orderDTO.getCost());
        orderMapper.addAppointment(order);
    }

    @Override
    public void deleteOrder(Integer orderId){
        Integer doctorId = orderMapper.selectDoctorIdByOrderId(orderId);
        int orderDay = orderMapper.selectOrderDayByOrderId(orderId);
        int orderTimeSlice = orderMapper.selectOrderTimeSliceByOrderId(orderId);
        if(orderMapper.selectOrderId(orderId) == null)
            throw new ServiceException(Constants.CODE_600, "该预约不存在");
        orderMapper.deleteOrder(orderId);
        doctorShiftInfoMapper.deletePatientNumber(doctorId, orderDay, orderTimeSlice);
    }
}
