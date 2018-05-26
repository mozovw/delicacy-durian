package com.delicacy.durian.state;

import com.delicacy.durian.state.pay.Strategy;
import com.delicacy.durian.state.pay.StrategyFactory;

import java.math.BigDecimal;

/**
 * 策略模式+工厂模式
 * @author zyt
 * @create 2018-05-26 18:00
 **/
public class Context {

    public BigDecimal calcDiscount(Integer chanelId, Integer goodsId) {
        StrategyFactory instance = StrategyFactory.getInstance();
        Strategy strategy = instance.create(chanelId);
        return strategy.calcDiscount(chanelId,goodsId);
    }
}
