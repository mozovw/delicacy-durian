package com.delicacy.durian.validation.error;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yutao
 * @create 2020-05-09 16:55
 **/
@Component
public class BasicExcetionHandler implements ExceptionHandler {
    @Override
    public void handle(Throwable error, Map map) {
        map.put("success",false);
        map.put("code", ((BasicException) error).getCode());
    }

    @Override
    public Boolean isExceptionType(Object object) {
        return object != null && object instanceof BasicException;
    }
}
