package com.delicacy.durian.state.v2;

import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author yutao
 * @create 2019-01-22 17:00
 **/
@Service
public class WechatPayProcessor extends AbstractPayProcessor{


    @Override
    public boolean preCheckedContext(PayContext payContext) {
        if (Objects.isNull(payContext.getData())){
            return false;
        }
        return true;
    }

    @Override
    public boolean doPay(PayContext payContext) {
        System.out.println("微信支付");
        return true;
    }

    @Override
    public String getPayChannel() {
        return PayChannelEnum.WECHAT_PAY.getChannel();
    }
}
