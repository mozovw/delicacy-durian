package com.delicacy.durian.state.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yutao
 * @create 2019-01-22 16:57
 **/
@Getter
@AllArgsConstructor
public enum PayChannelEnum {
    ALI_PAY("alipay","支付宝支付"),
    WECHAT_PAY("wechatpay","微信支付");
    private String channel;
    private String desc;
}
