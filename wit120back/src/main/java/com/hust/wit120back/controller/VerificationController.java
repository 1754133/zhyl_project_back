package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.service.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification")
public class VerificationController {

    @Autowired
    private SendSmsService sendSmsService;

    @PostMapping("/register")
    public Result sendRegisterMessage(@RequestParam String phone) throws Exception {
        if (StrUtil.isBlank(phone)){
            throw new ServiceException(Constants.CODE_400, "手机号为空");
        }
        return Result.success(sendSmsService.sendRegisterMessage(phone));
    }

    @PostMapping("/password")
    public Result sendPasswordMessage(@RequestParam String phone) throws Exception {
        if (StrUtil.isBlank(phone)){
            throw new ServiceException(Constants.CODE_400, "手机号为空");
        }
        return Result.success(sendSmsService.sendPasswordMessage(phone));
    }

}
