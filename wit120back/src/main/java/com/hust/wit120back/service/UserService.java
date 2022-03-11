package com.hust.wit120back.service;

import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.entity.User;

public interface UserService {
    void addUser(User user);
    UserDTO login(UserDTO userDTO);
    User getUserByUserId(Integer userId);
}
