package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class UserDTO {
    String username;
    String password;
    String phone;
    String code;
    String token;
}
