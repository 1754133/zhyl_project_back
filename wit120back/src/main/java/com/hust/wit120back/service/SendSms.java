package com.hust.wit120back.service;

import java.util.Map;

public interface SendSms {
    public boolean send(String phoneNum, String code) throws Exception;
}
