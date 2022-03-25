package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckResultMapper {
    @Select("select check_result from check_result where med_res_order_id = #{medResId}")
    String selectCheckResultByMedResOrderId(Integer medResId);

    @Insert("insert into check_result(med_res_order_id, check_result) values(#{medResId}, #{checkResult})")
    void addCheckResult(Integer medResId, String checkResult);
}
