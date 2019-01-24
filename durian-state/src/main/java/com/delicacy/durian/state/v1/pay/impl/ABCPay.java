package com.delicacy.durian.state.v1.pay.impl;

import com.delicacy.durian.state.v1.pay.Pay;
import com.delicacy.durian.state.v1.pay.Strategy;

import java.math.BigDecimal;

/**
 * @author zyt
 * @create 2018-05-26 17:54
 **/
@Pay(2)
public class ABCPay implements Strategy {
    @Override
    public BigDecimal calcDiscount(Integer chanelId, Integer goodsId) {
        return new BigDecimal(0.9);
    }
}
