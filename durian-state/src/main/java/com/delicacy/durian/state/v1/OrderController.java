package com.delicacy.durian.state.v1;


import com.delicacy.durian.state.v1.dto.Order;
import com.delicacy.durian.state.v1.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author zyt
 * @create 2018-05-26 17:31
 **/
@RequestMapping("order")
@RestController
public class OrderController {

    private static final Integer ICBC = 1;
    private static final Integer ABC = 2;


    @PostMapping("account")
    public BigDecimal calcAccount(Order order){
        if (ICBC== order.getPayChannelId()){

        }else if(ABC== order.getPayChannelId()){

        }
        return null;
    }

    @Autowired
    private Context context;

    /**
     * curl -H "Content-Type:application/json" -X POST  -d '{"payChannelId":1,"goodsId":1}' http://localhost:8080/order/account2
     * @param orderDto
     * @return
     */
    @PostMapping("account2")
    public BigDecimal calcAccount2(@RequestBody OrderDto orderDto){
        Order order = new Order();
        order.setGoodsId(order.getGoodsId());
        order.setPayChannelId(orderDto.getPayChannelId());
        BigDecimal bigDecimal = new BigDecimal(order.getValue());
        BigDecimal bigDecimal1 = context.calcDiscount(order.getPayChannelId(), order.getGoodsId());
        BigDecimal multiply = bigDecimal.multiply(bigDecimal1);
        return multiply.setScale(2,BigDecimal.ROUND_HALF_UP);
    }





}
