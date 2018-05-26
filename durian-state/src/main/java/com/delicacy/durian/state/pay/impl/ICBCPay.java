package com.delicacy.durian.state.pay.impl;

import com.delicacy.durian.state.pay.Pay;
import com.delicacy.durian.state.pay.Strategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author zyt
 * @create 2018-05-26 17:54
 **/
@Pay(1)
public class ICBCPay implements Strategy {
    @Override
    public BigDecimal calcDiscount(Integer chanelId, Integer goodsId) {
        return new BigDecimal(0.8);
    }
}
