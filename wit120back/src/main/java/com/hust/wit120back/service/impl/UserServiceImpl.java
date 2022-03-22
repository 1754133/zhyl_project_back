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

    @Autowired
    private MedicalTechnicianMapper medicalTechnicianMapper;

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
            order.setPatientName(patientInfoMapper.selectRealNameById(patientId));
            //设置医生姓名
            order.setDoctorName(docInfoMapper.selectDocNameById(order.getDoctorId()));
            //设置诊室名称
            Integer departmentId = docInfoMapper.selectDepartmentIdByDocId(order.getDoctorId());
            order.setDepartmentName(departmentMapper.selectDepartmentNameByDepartmentId(departmentId));
            //设置预约日期
            String date = TimeUtils.getOrderDate(order.getCreateTime(), order.getOrderDay());
            order.setOrderTime(date);
        }
        //将orders按照预约单创建时间排序
        Collections.sort(orders);
        return orders;
    }

    /**
     * 查询权限为4且未被分配医技的帐号
     * @return List<String>
     */
    @Override
    public List<String> getUsernameByPermission() {
        //存储结果的数组
        List<String> res = new ArrayList<>();
        //查询权限为4的所有账户
        List<User> userList1 = userMapper.selectUsersByPermission(4);
        if (userList1.size() == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到账户名信息");
        }
        //逐个判断每个账户是否已经分配了医技,如果没有则将用户名加入到结果数组中
        for (User user : userList1){
            if (medicalTechnicianMapper.selectMedicalTechnicianByDocId(user.getUserId()) == null){
                res.add(user.getUsername());
            }
        }
        return res;
    }

    /**
     * 根据用户名模糊查询医生账号
     * @param username
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getDocAccountByUsername(String username, int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        pageNum = (pageNum - 1) * pageSize;
        int count = userMapper.selectTotalByPermissionAndUsername(2, username);
        if (count == 0){
            throw new ServiceException(Constants.CODE_600, "未查询到任何医生帐号");
        }
        List<User> userList = userMapper.selectUserByPermissionAndUsername(2, username, pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("tableList", userList);
        res.put("total", count);
        return res;
    }
}
