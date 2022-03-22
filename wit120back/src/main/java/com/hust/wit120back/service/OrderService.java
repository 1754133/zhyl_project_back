package com.hust.wit120back.service;

import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.entity.Order;

public interface OrderService {
    void checkParameter(OrderDTO orderDTO);
    void addPatient(Integer doctorId, int orderDay, int orderTimeSlice);
    void addAppointment(OrderDTO orderDTO);
    void deleteOrder(Integer orderId);
}
