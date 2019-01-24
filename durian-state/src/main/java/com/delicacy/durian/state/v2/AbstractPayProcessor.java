package com.delicacy.durian.state.v2;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author yutao
 * @create 2019-01-22 16:51
 **/
@Service
public abstract class AbstractPayProcessor implements PayProcessor {


    @PostConstruct
    public void init() {
        payChannelMap.put(getPayChannel(), this);
    }

    @Override
    public void process(PayContext payContext) {
        // pre checked
        if (!preCheckedContext(payContext)) {
            // checked again
            System.out.println("error");
            return;
        }
        doPay(payContext);
    }

    /**
     * 对参数进行校验
     *
     * @param payContext
     * @return
     */
    public abstract boolean preCheckedContext(PayContext payContext);

    /**
     * 执行支付方法
     *
     * @param payContext
     * @return
     */
    public abstract boolean doPay(PayContext payContext);

    /**
     * 支付渠道编码
     *
     * @return
     */
    public abstract String getPayChannel();
}
