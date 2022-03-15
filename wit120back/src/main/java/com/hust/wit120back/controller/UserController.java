package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.PasswordDTO;
import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result addUser(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.addUser(userDTO));
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

    @PutMapping("/password")
    public Result updatePassword(@RequestBody PasswordDTO passwordDTO){
        String username = passwordDTO.getUsername();
        String oldPassword = passwordDTO.getOldPassword();
        String newPassword = passwordDTO.getNewPassword();
        String code = passwordDTO.getCode();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(oldPassword) || StrUtil.isBlank(code) || StrUtil.isBlank(newPassword)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.updatePassword(passwordDTO));
    }
}
