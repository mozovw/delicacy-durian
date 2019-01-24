package com.delicacy.durian.state.v1.dto;

import lombok.Data;

/**
 * @author zyt
 * @create 2018-05-26 17:45
 **/
@Data
public class Order {

    private Integer payChannelId;

    private String value = "100";

    private Integer goodsId;

}
