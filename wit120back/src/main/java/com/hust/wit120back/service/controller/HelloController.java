package com.hust.wit120back.service.controller;

import com.hust.wit120back.service.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private SendSms ss;

    @RequestMapping("/message")
    public void sendMessage() throws Exception {
        ss.send("17863991733", "7878");
        System.out.println("send() executed!");
    }

}
