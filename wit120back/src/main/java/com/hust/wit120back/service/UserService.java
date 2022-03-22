package com.hust.wit120back.service;

import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.PasswordDTO;
import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO login(UserDTO userDTO);
    User getUserByUserId(Integer userId);

    int updatePassword(PasswordDTO passwordDTO);

    Map<String, Object> getDocAccount(int pageNum, int pageSize);

    boolean addDoc(User user);

    boolean deleteDoc(Integer userId);

    List<OrderDTO> getAllOrders(Integer patientId);

    List<String> getUsernameByPermission();

    Map<String, Object> getDocAccountByUsername(String username, int pageNum, int pageSize);
}
