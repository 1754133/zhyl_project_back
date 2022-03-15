// This file is auto-generated, don't edit it. Thanks.
package com.hust.wit120back.entity;

import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;

public class Sms {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId:LTAI5tC3ui55bc8NdiVzVM6s
     * @param accessKeySecret:tcAiRuIwXiFFoIkGCaiuBYFKxtilUu
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
}