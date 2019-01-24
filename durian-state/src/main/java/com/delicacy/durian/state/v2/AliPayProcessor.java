package com.delicacy.durian.state.v2;

import org.springframework.stereotype.Service;

/**
 * @author yutao
 * @create 2019-01-22 17:00
 **/
@Service
public class AliPayProcessor extends AbstractPayProcessor{

    /**
     * 非公共校验
     * @param payContext
     * @return
     */
    @Override
    public boolean preCheckedContext(PayContext payContext) {
        if (payContext.getData()==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean doPay(PayContext payContext) {
        System.out.println("支付宝支付");
        return true;
    }

    @Override
    public String getPayChannel() {
        return PayChannelEnum.ALI_PAY.getChannel();
    }
}
