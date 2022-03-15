package com.hust.wit120back.mapper;

import com.hust.wit120back.entity.VerificationCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VerificationCodeMapper {
    @Select("select * from verification_code where phone=#{phone}")
    VerificationCode getCodeByPhone(String phone);

    @Insert("insert into verification_code(phone, code) values(#{phone}, #{code})")
    void addCode(String phone, String code);

    @Update("update verification_code set code=#{code} where phone=#{phoneNum}")
    void updateCode(String phoneNum, String code);
}
