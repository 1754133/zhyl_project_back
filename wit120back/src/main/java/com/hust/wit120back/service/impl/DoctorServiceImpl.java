package com.hust.wit120back.service.impl;

import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.PatientInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.*;
import com.hust.wit120back.service.DoctorService;
import com.hust.wit120back.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("DoctorService")
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PatientInfoMapper patientInfoMapper;

    @Autowired
    private DocInfoMapper docInfoMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public ArrayList<ConciseShiftInfoDTO> getDocConciseShiftInfo(Integer doctorId){
        ArrayList<ConciseShiftInfoDTO> shiftInfos = doctorMapper.selectDocConciseShiftInfoById(doctorId);
        if(shiftInfos.size() == 0)
            throw new ServiceException(Constants.CODE_503, "无坐诊信息");
        return shiftInfos;
    }

    @Override
    public ArrayList<ShiftInfoDTO> getDocShiftInfo(Integer doctorId){
        ArrayList<ShiftInfoDTO> shiftInfos = doctorMapper.selectDocShiftInfoById(doctorId);
        if(shiftInfos.size() == 0)
            throw new ServiceException(Constants.CODE_503, "无坐诊信息");
        return shiftInfos;
    }

    @Override
    public List<ShiftInfoDTO> getDocShiftInfoBySlice(Integer doctorId, int orderDay, int noon) {
        List<ShiftInfoDTO> shiftInfos;
        if (noon == 1){
            shiftInfos = doctorMapper.selectDocShiftInfoByIdAndDayAndMor(doctorId, orderDay);
        }else{
            shiftInfos = doctorMapper.selectDocShiftInfoByIdAndDayAndAft(doctorId, orderDay);
        }
        if (shiftInfos.size() == 0){
            throw new ServiceException(Constants.CODE_503, "无坐诊信息");
        }
        return shiftInfos;
    }

    @Override
    public List<OrderDTO> getPatientsByDate(Integer doctorId, String date){
        List<OrderDTO> orders = orderMapper.selectOrdersByDocId(doctorId);
        List<OrderDTO> todayOrders = new ArrayList<OrderDTO>();
        for(OrderDTO order : orders){
            //设置预约日期
            String orderDate = TimeUtils.getOrderDate(order.getCreateTime(), order.getOrderDay());
            if(!orderDate.equals(date)) continue;
            order.setOrderTime(orderDate);
            //设置病人姓名
            order.setPatientName(patientInfoMapper.selectRealNameById(order.getPatientId()));
            //设置性别
            order.setGender(patientInfoMapper.selectGenderById(order.getPatientId()));
            //设置诊室名称
            Integer departmentId = docInfoMapper.selectDepartmentIdByDocId(order.getDoctorId());
            order.setDepartmentName(departmentMapper.selectDepartmentNameByDepartmentId(departmentId));
            todayOrders.add(order);
        }
        //将orders按照预约单创建时间排序
        Collections.sort(todayOrders);
        return todayOrders;
    }

}
