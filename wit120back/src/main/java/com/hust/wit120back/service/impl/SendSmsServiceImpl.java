package com.hust.wit120back.service.impl;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.entity.Sms;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.UserMapper;
import com.hust.wit120back.mapper.VerificationCodeMapper;
import com.hust.wit120back.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("SendSms")
public class SendSmsServiceImpl implements SendSmsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Override
    public String send(String phoneNum) throws Exception{
        if (userMapper.selectUserByPhone(phoneNum) != null){
            throw new ServiceException(Constants.CODE_700, "手机号已被注册");
        }
        //随机生成验证码
        String code = String.valueOf(new Random().nextInt(7999) + 1000);
        verificationCodeMapper.addCode(phoneNum, code);
        com.aliyun.dysmsapi20170525.Client client = Sms.createClient("LTAI5tC3ui55bc8NdiVzVM6s", "tcAiRuIwXiFFoIkGCaiuBYFKxtilUu");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(phoneNum)
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendResp = client.sendSms(sendSmsRequest);
        String codeInfo = sendResp.body.code;
        System.out.println("check point");
        //判断是否发送成功
        if(!com.aliyun.teautil.Common.equalString(codeInfo, "OK")){
            throw new ServiceException(Constants.CODE_400, "短信发送失败");
        }else{
            return code;
        }
    }
}
