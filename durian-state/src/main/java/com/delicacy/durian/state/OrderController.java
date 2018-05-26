package com.delicacy.durian.state;

import com.delicacy.durian.state.dto.Order;
import com.delicacy.durian.state.dto.OrderDto;
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

    @PostMapping("account2")
    public BigDecimal calcAccount2(@RequestBody OrderDto orderDto){
        Context context = new Context();
        Order order = new Order();
        order.setGoodsId(order.getGoodsId());
        order.setPayChannelId(orderDto.getPayChannelId());
        BigDecimal bigDecimal = new BigDecimal(order.getValue());
        BigDecimal bigDecimal1 = context.calcDiscount(order.getPayChannelId(), order.getGoodsId());
        BigDecimal multiply = bigDecimal.multiply(bigDecimal1);
        return multiply.setScale(2,BigDecimal.ROUND_HALF_UP);
    }





}
