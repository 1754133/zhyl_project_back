package com.hust.wit120back.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.PasswordDTO;
import com.hust.wit120back.dto.UserDTO;
import com.hust.wit120back.entity.Order;
import com.hust.wit120back.entity.User;
import com.hust.wit120back.entity.VerificationCode;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.*;
import com.hust.wit120back.service.UserService;
import com.hust.wit120back.util.TimeUtils;
import com.hust.wit120back.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Autowired
    private PatientInfoMapper patientInfoMapper;

    @Autowired
    private DocInfoMapper docInfoMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

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

    @Override
    public Map<String, Object> getDocAccount(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int count = userMapper.selectTotalByPermission(2);
        if (count == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何医生帐号");
        }
        List<User> userList = userMapper.selectUserByPermission(2, pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", userList);
        res.put("total", count);
        return res;
    }

    @Override
    public boolean addDoc(User user) {
        String username = user.getUsername();
        String phone = user.getPhone();
        if (userMapper.selectUserByUsername(username) != null){
            throw new ServiceException(Constants.CODE_700, "用户名已被注册");
        }
        if (userMapper.selectUserByPhone(phone) != null){
            throw new ServiceException(Constants.CODE_700, "手机号已被注册");
        }
        //赋予医生权限
        user.setPermission(2);
        //存入数据库
        userMapper.addUser(user);
        user = userMapper.selectUserByUsername(username);
        //在医生信息表中添加一条数据
        docInfoMapper.addDocInfo(user.getUserId());
        return true;
    }

    @Override
    public boolean deleteDoc(Integer userId) {
        User user = userMapper.selectUserByUserIdAndPermission(userId, 2);
        if (user == null){
            throw new ServiceException(Constants.CODE_600, "要删除的医生帐号不存在");
        }
        //删除医生信息
        docInfoMapper.deleteDocInfoByDocId(userId);
        //删除医生账号
        userMapper.deleteUserByUserId(userId);
        return true;
    }

    @Override
    public List<OrderDTO> getAllOrders(Integer patientId){
        List<OrderDTO> orders = orderMapper.selectOrders(patientId);
        for(OrderDTO order : orders){
            //设置病人姓名
            order.setUsername(patientInfoMapper.selectRealNameById(patientId));
            //设置医生姓名
            order.setDoctorName(docInfoMapper.selectDocNameById(order.getDoctorId()));
            //设置诊室名称
            Integer departmentId = docInfoMapper.selectDepartmentIdByDocId(order.getDoctorId());
            order.setDepartmentName(departmentMapper.selectDepartmentNameByDepartmentId(departmentId));
            //设置预约日期
            //通过创建日期明确订单创建时是周几
            String date = order.getCreateTime().substring(0, 10);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            try{
                d = f.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.setTime(d);
            int createDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if(createDay == 0) createDay = 7;
            //与orderDay进行比较
            int orderDay = order.getOrderDay();
            if(orderDay == 0) orderDay = 7;
            String createDate = order.getCreateTime().substring(0, 10);
            String orderDate;
            if(orderDay >= createDay) orderDate = TimeUtils.calDate(createDate, orderDay - createDay);
            else orderDate = TimeUtils.calDate(createDate, orderDay + 7 - createDay);
            order.setOrderTime(orderDate);
        }
        //将orders按照预约单创建时间排序
        Collections.sort(orders);
        return orders;
    }
}
