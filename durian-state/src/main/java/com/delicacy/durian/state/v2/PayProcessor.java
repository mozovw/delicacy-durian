package com.delicacy.durian.state.v2;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zyt
 * @create 2019-01-22 16:50
 **/
public interface PayProcessor {
    ConcurrentHashMap<String,PayProcessor> payChannelMap = new ConcurrentHashMap<>();
    void process(PayContext payContext);
}
