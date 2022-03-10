package com.hust.wit120back.service.impl;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.hust.wit120back.entity.SmsEntity;
import com.hust.wit120back.service.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("SendSms")
public class SendSmsImpl implements SendSms {

    @Override
    public boolean send(String phoneNum, String code) throws Exception{
        com.aliyun.dysmsapi20170525.Client client = SmsEntity.createClient("LTAI5tC3ui55bc8NdiVzVM6s", "tcAiRuIwXiFFoIkGCaiuBYFKxtilUu");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(phoneNum)
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendResp = client.sendSms(sendSmsRequest);
        String codeInfo = sendResp.body.code;
        System.out.println("check point");
        //判断是否发送成功
        if(!com.aliyun.teautil.Common.equalString(codeInfo, "OK")) return false;
        return true;
    }
}
