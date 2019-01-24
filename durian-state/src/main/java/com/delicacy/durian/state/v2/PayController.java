package com.delicacy.durian.state.v2;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyt
 * @create 2018-05-26 17:31
 **/
@RequestMapping("pay")
@RestController
public class PayController {
    /**
     * curl -H "Content-Type:application/json" -X POST  -d '{}' http://localhost:8080/pay/wechat
     * @param payment
     */
    @PostMapping("wechat")
    public void wechat(Payment payment) {

        //payContext
        PayContext<Payment> payContext = new PayContext<>();
        payContext.setData(payment);
        PayProcessor.payChannelMap.get(PayChannelEnum.WECHAT_PAY.getChannel()).process(payContext);
    }

    static class Payment{}




}
