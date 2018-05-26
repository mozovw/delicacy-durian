package com.delicacy.durian.state.pay;

import java.math.BigDecimal;

/**
 * @author
 * @create 2018-05-26 17:51
 **/
public interface Strategy {

    BigDecimal calcDiscount(Integer chanelId, Integer goodsId);

}
