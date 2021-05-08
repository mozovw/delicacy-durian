package com.delicacy.durian.dingtalk.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yutao
 * @create 2020-04-20 14:30
 **/
@Data
@Configuration
@ConfigurationProperties("dingtalk")
@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
public class DingTalkProperties {

    private MsgInfo msg;
    private String appKey;
    private String appSecret;
    private Long agentId;
}

