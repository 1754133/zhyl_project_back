package com.hust.wit120back.service.impl;

import cn.hutool.core.util.StrUtil;
import com.hust.wit120back.common.Constants;
import com.hust.wit120back.dto.ConciseShiftInfoDTO;
import com.hust.wit120back.dto.OrderDTO;
import com.hust.wit120back.dto.PatientInfoDTO;
import com.hust.wit120back.dto.ShiftInfoDTO;
import com.hust.wit120back.exception.ServiceException;
import com.hust.wit120back.mapper.*;
import com.hust.wit120back.service.DoctorService;
import com.hust.wit120back.util.TimeUtils;
import org.apache.tomcat.util.bcel.Const;
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

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @Autowired
    private CaseHistoryMapper caseHistoryMapper;

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
            //病历
            String caseHistory = caseHistoryMapper.selectCaseHisByOrderId(order.getOrderId());
            //处方
            String prescription = prescriptionMapper.selectPrescByOrderId(order.getOrderId());
            if(StrUtil.isBlank(caseHistory) && StrUtil.isBlank(prescription))
                order.setDeal(false);
            else
                order.setDeal(true);
            todayOrders.add(order);
        }
        //将orders按照预约单预约时间排序
        Collections.sort(todayOrders);
        return todayOrders;
    }

    @Override
    public boolean addPrescription(Integer orderId, String prescription){
        if(prescriptionMapper.selectPrescIdByOrderId(orderId) != null){
            prescriptionMapper.updatePrescription(orderId, prescription);
        }else{
            prescriptionMapper.addPrescription(orderId, prescription);
        }
        return true;
    }

    @Override
    public String getPrescription(Integer orderId){
        if(prescriptionMapper.selectPrescIdByOrderId(orderId) == null)
            throw new ServiceException(Constants.CODE_600, "无对应处方");
        return prescriptionMapper.selectPrescByOrderId(orderId);
    }

    @Override
    public int updatePrescription(Integer orderId, String prescription){
        if(prescriptionMapper.selectPrescIdByOrderId(orderId) == null)
            throw new ServiceException(Constants.CODE_600, "无对应处方");
        return prescriptionMapper.updatePrescription(orderId, prescription);
    }

    @Override
    public boolean addCaseHistory(Integer orderId, String caseHistory){
        if(caseHistoryMapper.selectCaseHisIdByOrderId(orderId) != null){
            caseHistoryMapper.updateCaseHistory(orderId, caseHistory);
        }else{
            caseHistoryMapper.addCaseHistory(orderId, caseHistory);
        }
        return true;
    }

    @Override
    public String getCaseHistory(Integer orderId){
        if(caseHistoryMapper.selectCaseHisIdByOrderId(orderId) == null)
            throw new ServiceException(Constants.CODE_600, "无对应病历");
        return caseHistoryMapper.selectCaseHisByOrderId(orderId);
    }

    @Override
    public int updateCaseHistory(Integer orderId, String caseHistory){
        if(caseHistoryMapper.selectCaseHisIdByOrderId(orderId) == null)
            throw new ServiceException(Constants.CODE_600, "无对应病历");
        return caseHistoryMapper.updateCaseHistory(orderId, caseHistory);
    }

    @Override
    public boolean addShiftInfo(ConciseShiftInfoDTO conciseShiftInfoDTO) {
        int day = conciseShiftInfoDTO.getDay();
        if (day < 0 || day > 6){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        int noon = conciseShiftInfoDTO.getNoon();
        if (noon < 1 || noon > 2){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        ConciseShiftInfoDTO conciseShiftInfoDTO1 = doctorMapper.selectConciseShiftInfoByDoctorIdAndDayAndNoon(conciseShiftInfoDTO);
        if (conciseShiftInfoDTO1 != null){
            throw new ServiceException(Constants.CODE_700, "坐诊信息重复，添加失败");
        }
        doctorMapper.addConciseShiftInfo(conciseShiftInfoDTO);
        if (conciseShiftInfoDTO.getNoon() == 1){
            for (int i = 1; i < 4; i++){
                doctorMapper.addShiftInfo(conciseShiftInfoDTO.getDoctorId(), conciseShiftInfoDTO.getDay(), i, 0);
            }
        }else{
            for (int i = 4; i < 7; i++){
                doctorMapper.addShiftInfo(conciseShiftInfoDTO.getDoctorId(), conciseShiftInfoDTO.getDay(), i, 0);
            }
        }
        return true;
    }

    @Override
    public boolean deleteShiftInfo(Integer doctorId, int day, int noon) {
        if (day < 0 || day > 6 || noon < 1 || noon > 2){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }
        ConciseShiftInfoDTO conciseShiftInfoDTO = new ConciseShiftInfoDTO();
        conciseShiftInfoDTO.setDoctorId(doctorId);
        conciseShiftInfoDTO.setDay(day);
        conciseShiftInfoDTO.setNoon(noon);
        //判断删除的粗略信息是否不存在
        ConciseShiftInfoDTO conciseShiftInfoDTO1 = doctorMapper.selectConciseShiftInfoByDoctorIdAndDayAndNoon(conciseShiftInfoDTO);
        if (conciseShiftInfoDTO1 == null){
            throw new ServiceException(Constants.CODE_600, "要删除的坐诊信息不存在");
        }
        //判断删除的详细表信息是否能删除
        if (noon == 1){
            for (int i = 1; i < 4; i++){
                ShiftInfoDTO shiftInfoDTO = doctorMapper.selectShiftInfoByDoctorIdAndDayAndNoon(doctorId, day, i);
                if (shiftInfoDTO == null){
                    throw new ServiceException(Constants.CODE_600, "要删除的详细坐诊信息不存在");
                }
                if (shiftInfoDTO.getPatientsNumber() != 0){
                    throw new ServiceException(Constants.CODE_700, "该医生该时段已有患者预约，删除失败");
                }
            }
        }else{
            for (int i = 4; i < 7; i++){
                ShiftInfoDTO shiftInfoDTO = doctorMapper.selectShiftInfoByDoctorIdAndDayAndNoon(doctorId, day, i);
                if (shiftInfoDTO == null){
                    throw new ServiceException(Constants.CODE_600, "要删除的详细坐诊信息不存在");
                }
                if (shiftInfoDTO.getPatientsNumber() != 0){
                    throw new ServiceException(Constants.CODE_700, "该医生该时段已有患者预约，删除失败");
                }
            }
        }
        //删除详细表中的信息
        if (noon == 1){
            for (int i = 1; i < 4; i++){
                doctorMapper.deleteShiftInfo(doctorId, day, i);
            }
        }else{
            for (int i = 4; i < 7; i++){
                doctorMapper.deleteShiftInfo(doctorId, day, i);
            }
        }
        //删除粗略表中的信息
        doctorMapper.deleteConciseShiftInfo(doctorId, day, noon);
        return true;
    }
}
