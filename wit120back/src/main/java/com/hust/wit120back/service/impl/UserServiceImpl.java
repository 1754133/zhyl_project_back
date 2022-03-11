package com.hust.wit120back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.entity.User;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.UserMapper;
import com.hust.wit120back.service.UserService;
import com.hust.wit120back.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.addUser(user);
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
}
