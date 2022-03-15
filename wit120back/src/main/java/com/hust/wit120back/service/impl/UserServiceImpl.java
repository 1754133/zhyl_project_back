package com.hust.wit120back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.PasswordDTO;
import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.entity.PatientInfo;
import com.hust.wit120back.entity.User;
import com.hust.wit120back.entity.VerificationCode;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.PatientInfoMapper;
import com.hust.wit120back.mapper.UserMapper;
import com.hust.wit120back.mapper.VerificationCodeMapper;
import com.hust.wit120back.service.UserService;
import com.hust.wit120back.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Autowired
    private PatientInfoMapper patientInfoMapper;

    public UserDTO addUser(UserDTO userDTO){
        if (userMapper.selectUserByUsername(userDTO.getUsername()) != null){
            throw new ServiceException(Constants.CODE_700, "用户名已被注册");
        }
        if (userMapper.selectUserByPhone(userDTO.getPhone()) != null){
            throw new ServiceException(Constants.CODE_700, "手机号已被注册");
        }
        VerificationCode verificationCode = verificationCodeMapper.getCodeByPhone(userDTO.getPhone());
        if (!verificationCode.getCode().equals(userDTO.getCode())){
            throw new ServiceException(Constants.CODE_700, "验证码错误");
        }
        //存储数据库
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setPermission(1);
        userMapper.addUser(user);
        user = userMapper.selectUserByUsername(userDTO.getUsername());
        //在病人信息表里自动添加一条数据
        patientInfoMapper.addPatientInfo(user.getUserId());
        //设置返回值
        //注册之后自动登录
        String token = TokenUtils.genToken(user.getUserId().toString(), user.getPassword());
        userDTO.setCode("");
        userDTO.setToken(token);
        return userDTO;
    }

    public UserDTO login(UserDTO userDTO) {
        User user = userMapper.selectUserByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
        if (user != null){
            BeanUtil.copyProperties(user, userDTO, true);
            String token = TokenUtils.genToken(user.getUserId().toString(), user.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else{
            throw new ServiceException(Constants.CODE_700, "用户名或密码错误");
        }
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public int updatePassword(PasswordDTO passwordDTO) {

        User user = userMapper.selectUserByUsernameAndPassword(passwordDTO.getUsername(), passwordDTO.getOldPassword());
        if (user != null){
            VerificationCode verificationCode = verificationCodeMapper.getCodeByPhone(user.getPhone());
            if (!verificationCode.getCode().equals(passwordDTO.getCode())){
                throw new ServiceException(Constants.CODE_700, "验证码错误");
            }
            return userMapper.updatePassword(passwordDTO.getNewPassword(), passwordDTO.getUsername());
        }else {
            throw new ServiceException(Constants.CODE_700, "密码错误");
        }
    }
}
