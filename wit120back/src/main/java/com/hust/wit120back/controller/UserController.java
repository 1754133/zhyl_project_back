package com.hust.wit120back.controller;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.common.Result;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.PasswordDTO;
import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.entity.User;
import com.hust.wit120back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 新增医生帐号
     * @param user
     * @return
     */
    @PostMapping("/doc")
    public Result addDoc(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String phone = user.getPhone();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password) || StrUtil.isBlank(phone)){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.addDoc(user));
    }

    @DeleteMapping("/doc/{userId}")
    public Result deleteDoc(@PathVariable Integer userId){
        if (userId == null){
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.deleteDoc(userId));
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

    /**
     * 分页查询所有的医生帐号
     * @param
     * @return Result
     */
    @GetMapping("/doc/{pageNum}/{pageSize}")
    public Result getDocAccount(@PathVariable int pageNum, @PathVariable int pageSize){
        return Result.success(userService.getDocAccount(pageNum, pageSize));
    }

    @GetMapping("/orders/{patientId}")
    public Result getAllOrders(@PathVariable Integer patientId){
        List<OrderDTO> orders = userService.getAllOrders(patientId);
        if(orders.size() == 0)
            return Result.error(Constants.CODE_600, "无预约记录");
        return Result.success(orders);
    }

    /**
     * 查询可用的医技负责帐号
     * @return result
     */
    @GetMapping("/username")
    public Result getUsernameByPermission(){
        return Result.success(userService.getUsernameByPermission());
    }

}
