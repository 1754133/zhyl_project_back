package com.hust.wit120back.entity;

import lombok.Data;

@Data
public class User {
    Integer userId;
    String username;
    String password;
    String phone;
    int permission;
}
