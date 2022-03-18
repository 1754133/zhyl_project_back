package com.hust.wit120back.common;

/**
 * 常量类
 *
 分类	分类描述
 1**	信息，服务器收到请求，需要请求者继续执行操作
 2**	成功，操作被成功接收并处理
 3**	重定向，需要进一步的操作以完成请求
 4**	客户端错误，请求包含语法错误或无法完成请求
 5**	服务器错误，服务器在处理请求的过程中发生了错误
 */

public interface Constants {
    String CODE_200 = "200"; //请求成功
    String CODE_400 = "400"; //参数错误
    String CODE_401 = "401"; //权限不足
    String CODE_402 = "402"; //用户未登录
    String CODE_403 = "403"; //该科室不存在
    String CODE_404 = "404"; //网页不存在
    String CODE_500 = "500"; //系统错误
    String CODE_501 = "501"; //该时段挂号名额已满或该医生并未坐诊
    String CODE_502 = "502"; //该用户已预约
    String CODE_503 = "503"; //无坐诊信息
    String CODE_700 = "700"; //其他业务异常
}
