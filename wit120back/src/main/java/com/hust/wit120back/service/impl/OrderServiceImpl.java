package com.hust.wit120back.service.impl;

import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.entity.Order;
import com.hust.wit120back.mapper.OrderMapper;
import com.hust.wit120back.mapper.UserMapper;
import com.hust.wit120back.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     *是否已经预约过
     */
    @Override
    public boolean hasMakeAppointment(String username, String doctorName, int orderDay, int orderTimeSlice){
        Integer patientId = orderMapper.selectPatientIdByName(username);
        Integer doctorId = orderMapper.selectDoctorIdByName(doctorName);
        if(orderMapper.selectOrder(patientId, doctorId, orderDay, orderTimeSlice) != null)
            return true;
        return false;
    }

    /**
     *查询是否某医生该时段是否还有名额（每小时最多四个人）
     */
    @Override
    public boolean isAppointmentLeft(String doctorName, int orderDay, int orderTimeSlice){
        Integer doctorId = orderMapper.selectDoctorIdByName(doctorName);
        Integer patientsNum = orderMapper.selectNumAppointmentByDoctorAndTime(doctorId, orderDay, orderTimeSlice);
        System.out.println("paitentNum: " + patientsNum);
        if(patientsNum == null || patientsNum >= 4) return false;
        return true;
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
