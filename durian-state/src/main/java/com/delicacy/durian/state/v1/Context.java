package com.delicacy.durian.state.v1;

import com.delicacy.durian.state.v1.pay.Strategy;
import com.delicacy.durian.state.v1.pay.StrategyFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 策略模式+工厂模式
 * @author zyt
 * @create 2018-05-26 18:00
 **/
@Service
public class Context {

    public BigDecimal calcDiscount(Integer chanelId, Integer goodsId) {
        StrategyFactory instance = StrategyFactory.getInstance();
        Strategy strategy = instance.create(chanelId);
        return strategy.calcDiscount(chanelId,goodsId);
    }
}
