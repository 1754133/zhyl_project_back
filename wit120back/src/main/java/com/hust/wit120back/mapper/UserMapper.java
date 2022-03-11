package com.hust.wit120back.mapper;

import com.hust.wit120back.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(username, password, phone, permission) values(#{username}, #{password}, #{phone}, #{permission})")
    void addUser(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    @Result(column = "user_id", property = "userId")
    User selectUserByUsernameAndPassword(String username, String password);

    @Select("select * from user where user_id=#{userId}")
    @Result(column = "user_id", property = "userId")
    User selectUserByUserId(Integer userId);
}
