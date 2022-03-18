package com.hust.wit120back.service;

import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.entity.Order;

public interface OrderService {
    void checkParameter(OrderDTO orderDTO);
    void addPatient(String username, String doctorName, int orderDay, int orderTimeSlice);
    void addAppointment(OrderDTO orderDTO);
}
