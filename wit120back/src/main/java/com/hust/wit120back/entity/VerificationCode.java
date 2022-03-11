package com.hust.wit120back.entity;

import lombok.Data;

@Data
public class VerificationCode {
    private String phone;
    private String code;
}
