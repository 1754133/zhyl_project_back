package com.hust.wit120back.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ResourceRecommendMapper {
    @Insert("insert into resource_recommend(order_id, resource_recommend) values(#{orderId}, #{recommend})")
    void addRecommend(Integer orderId, String recommend);

    @Update("update resource_recommend set resource_recommend = #{recommend} where order_id = #{orderId}")
    void updateRecommend(Integer orderId, String recommend);

    @Select("select order_id from resource_recommend where order_id = #{orderId}")
    Integer selectOrderId(Integer orderId);

}
