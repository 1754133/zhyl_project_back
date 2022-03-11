package com.hust.wit120back.service;

import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.entity.User;

import java.util.Map;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO login(UserDTO userDTO);
    User getUserByUserId(Integer userId);
}
