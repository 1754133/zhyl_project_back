package com.hust.wit120back.service;

import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.entity.Order;

public interface OrderService {
    public boolean hasMakeAppointment(String username, String doctorName, int orderDay, int orderTimeSlice);
    public boolean isAppointmentLeft(String doctorName, int orderDay, int orderTimeSlice);
    public void addPatient(String username, String doctorName, int orderDay, int orderTimeSlice);
    public void addAppointment(OrderDTO orderDTO);
}
