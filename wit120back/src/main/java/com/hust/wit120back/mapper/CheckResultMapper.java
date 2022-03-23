package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckResultMapper {
    @Select("select check_result from check_result where med_res_order_id = #{medResId}")
    String selectCheckResultByMedResOrderId(Integer medResId);
}
