package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String phone;
    private String code;
    private String token;
    private int permission;
}
