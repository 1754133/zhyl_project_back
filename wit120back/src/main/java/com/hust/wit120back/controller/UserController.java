package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.entity.User;
import com.hust.wit120back.service.UserService;
import com.hust.wit120back.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/verification")
    public String getVerificationCode(@RequestParam String verificationCode){
        System.out.println(verificationCode);
        return "ok";
    }

    @PostMapping
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "ok";
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.login(userDTO));
    }
}
