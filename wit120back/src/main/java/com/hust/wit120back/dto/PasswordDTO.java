package com.hust.wit120back.dto;

import lombok.Data;

@Data
public class PasswordDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String code;
}
