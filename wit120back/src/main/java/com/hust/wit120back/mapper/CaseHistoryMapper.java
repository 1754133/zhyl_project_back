package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CaseHistoryMapper {
    @Select("select case_history_id from case_history where order_id = #{orderId}")
    Integer selectCaseHisIdByOrderId(Integer orderId);

    @Select("select case_history from case_history where order_id = #{orderId}")
    String selectCaseHisByOrderId(Integer orderId);

    @Insert("insert into case_history(order_id, case_history) values(#{orderId}, #{caseHistory})")
    void addCaseHistory(Integer orderId, String caseHistory);

    @Update("update case_history set case_history = #{caseHistory} where order_id = #{orderId}")
    int updateCaseHistory(Integer orderId, String caseHistory);
}
