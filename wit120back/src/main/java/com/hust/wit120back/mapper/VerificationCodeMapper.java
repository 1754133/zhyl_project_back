package com.hust.wit120back.mapper;

import com.hust.wit120back.entity.VerificationCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VerificationCodeMapper {
    @Select("select * from verification_code where phone=#{phone}")
    VerificationCode getCodeByPhone(String phone);

    @Insert("insert into verification_code(phone, code) values(#{phone}, #{code})")
    void addCode(String phone, String code);
}
