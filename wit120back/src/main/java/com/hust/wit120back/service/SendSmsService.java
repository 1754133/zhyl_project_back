package com.hust.wit120back.service;

import java.util.Map;

public interface SendSmsService {

    String sendRegisterMessage(String phone) throws Exception;

    String sendPasswordMessage(String phone) throws Exception;
}
