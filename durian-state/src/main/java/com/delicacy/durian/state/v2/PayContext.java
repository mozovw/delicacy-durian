package com.delicacy.durian.state.v2;

import lombok.Data;

/**
 * @author yutao
 * @create 2019-01-22 16:51
 **/
@Data
public class PayContext<T> {
    private String msg;
    //private Integer cents;
    private T data;
}
